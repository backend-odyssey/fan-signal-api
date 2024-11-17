package backend_incubator.fan_signal_api.post.dto

data class PostCreateDto(
    val memberId: String = "",
    val content: String = "",
    val photos: List<String> = emptyList(),
)
