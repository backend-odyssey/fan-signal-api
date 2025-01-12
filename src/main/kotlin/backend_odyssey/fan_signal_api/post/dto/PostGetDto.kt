package backend_odyssey.fan_signal_api.post.dto

data class PostGetDto(
    val id: String,
    val content: String,
    val photos: List<String>,
    val author: String,
    val authorId: String,
    val authorPhoto: String? = null,
    val likeCount: Long,
    val commentCount: Long,
    val isFollowing: Boolean,
    val isBookmark: Boolean,
    val isLike: Boolean,
)