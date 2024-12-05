package backend_incubator.fan_signal_api.post

import backend_incubator.fan_signal_api.exception.CustomException
import backend_incubator.fan_signal_api.exception.ErrorCode
import backend_incubator.fan_signal_api.post.dto.PostCreateDto
import backend_incubator.fan_signal_api.post.dto.PostUpdateDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PostService(private val postRepository: PostRepository) {
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

}