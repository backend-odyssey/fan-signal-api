package backend_odyssey.fan_signal_api.post

import backend_odyssey.fan_signal_api.common.ApiResponse
import backend_odyssey.fan_signal_api.post.dto.PostCreateDto
import backend_odyssey.fan_signal_api.post.dto.PostGetDto
import backend_odyssey.fan_signal_api.post.dto.PostUpdateDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/post")
class PostController(private val postService: PostService) {
    @PostMapping("")
    fun createPost(@Valid @RequestBody postCreateDto: PostCreateDto): Mono<ApiResponse<Void>> {
        return postService.createPost(postCreateDto).then(Mono.just(ApiResponse.success()))
    }

    @PutMapping("/{id}")
    fun updatePost(
        @Valid @RequestBody postUpdateDto: PostUpdateDto,
        @PathVariable id: String
    ): Mono<ApiResponse<Void>> {
        return postService.updatePost(postUpdateDto, id).then(Mono.just(ApiResponse.success()))
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: String): Mono<ApiResponse<Void>> {
        return postService.deletePost(id).then(Mono.just(ApiResponse.success()))
    }

    @GetMapping("/{id}")
    fun readPost(@PathVariable id: String, @RequestParam memberId: String): Mono<ApiResponse<PostGetDto>> {
        return postService.readPost(id, memberId).map { ApiResponse.success(it) }
    }
}