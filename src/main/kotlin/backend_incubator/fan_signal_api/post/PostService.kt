package backend_incubator.fan_signal_api.post

import backend_incubator.fan_signal_api.exception.CustomException
import backend_incubator.fan_signal_api.exception.ErrorCode
import backend_incubator.fan_signal_api.extensions.component1
import backend_incubator.fan_signal_api.extensions.component2
import backend_incubator.fan_signal_api.extensions.component3
import backend_incubator.fan_signal_api.extensions.component4
import backend_incubator.fan_signal_api.post.dto.PostCreateDto
import backend_incubator.fan_signal_api.post.dto.PostGetDto
import backend_incubator.fan_signal_api.post.dto.PostUpdateDto
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PostService(
    private val postRepository: PostRepository,
    private val redisTemplate: ReactiveStringRedisTemplate
) {
    fun createPost(postCreateDto: PostCreateDto): Mono<Post> {
        val post = Post(
            memberId = postCreateDto.memberId,
            content = postCreateDto.content,
            photos = postCreateDto.photos,
        )
        return postRepository.save(post)
    }

    fun updatePost(postUpdateDto: PostUpdateDto, id: String): Mono<Post> {
        return postRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(ErrorCode.POST_NOT_FOUND)))
            .flatMap { post ->
                post.content = postUpdateDto.content
                post.photos = postUpdateDto.photos
                postRepository.save(post)
            }
    }

    fun deletePost(id: String): Mono<Void> {
        return postRepository.deleteById(id)
    }

    fun readPost(id: String, memberId: String): Mono<PostGetDto> {
        // TODO. isBookmark & isLike 추후 개발
        return postRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(ErrorCode.POST_NOT_FOUND)))
            .flatMap { post ->
                val isFollowingKey = "following:$memberId"
                val memberKey = "member:${post.memberId}"
                val likeCountKey = "post:${id}:like_count"
                val commentCountKey = "post:${id}:comment_count"

                val isFollowingMono = redisTemplate.opsForSet().isMember(isFollowingKey, post.memberId)
                val memberDataMono = redisTemplate.opsForHash<String, String>()
                    .entries(memberKey).collectMap({ it.key }, { it.value })
                val likeCountMono = redisTemplate.opsForValue().get(likeCountKey).map(String::toLong).defaultIfEmpty(0L)
                val commentCountMono = redisTemplate.opsForValue().get(commentCountKey)
                    .map(String::toLong).defaultIfEmpty(0L)

                Mono.zip(isFollowingMono, memberDataMono, likeCountMono, commentCountMono)
                    .map { (isFollowing, memberData, likeCount, commentCount) ->
                        PostGetDto(
                            id = id,
                            content = post.content,
                            photos = post.photos,
                            author = memberData["nickname"] ?: "Unknown",
                            authorId = post.memberId,
                            authorPhoto = memberData["photo"],
                            likeCount = likeCount,
                            commentCount = commentCount,
                            isFollowing = isFollowing,
                            isBookmark = true,
                            isLike = false
                        )
                    }
            }
    }
}