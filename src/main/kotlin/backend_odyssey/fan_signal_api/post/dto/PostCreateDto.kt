package backend_odyssey.fan_signal_api.post.dto

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class PostCreateDto(
    @NotBlank val memberId: String = "",
    @NotBlank val content: String = "",
    @NotNull val photos: List<String>,
)
