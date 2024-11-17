package backend_incubator.fan_signal_api.post

import backend_incubator.fan_signal_api.common.ApiResponse
import backend_incubator.fan_signal_api.post.dto.PostCreateDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/post")
class PostController(private val postService: PostService) {
    @PostMapping("")
    fun createPost(@RequestBody postCreateDto: PostCreateDto): Mono<ApiResponse<Void>> {
        return postService.createPost(postCreateDto)
    }
}