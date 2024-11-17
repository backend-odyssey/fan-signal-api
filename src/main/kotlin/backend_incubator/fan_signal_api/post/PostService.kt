package backend_incubator.fan_signal_api.post

import backend_incubator.fan_signal_api.common.ApiResponse
import backend_incubator.fan_signal_api.post.dto.PostCreateDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PostService(private val postRepository: PostRepository) {
    fun createPost(postCreateDto: PostCreateDto): Mono<ApiResponse<Void>> {
        val post = Post(
            memberId = postCreateDto.memberId,
            content = postCreateDto.content,
            photos = postCreateDto.photos,
        )
        return postRepository.save(post)
            .then(Mono.just(ApiResponse.success()));
    }
}