package backend_incubator.fan_signal_api.post.dto

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class PostUpdateDto(
    @NotBlank val content: String = "",
    @NotNull val photos: List<String>,
)