package backend_odyssey.fan_signal_api.post

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

@Document(collection = "post")
data class Post(
    @Id
    val id: String? = null,
    @Field("member_id")
    val memberId: String,
    var content: String,
    var photos: List<String>,
    @CreatedDate @Field("created_at")
    var createdAt: LocalDateTime? = null,
    @LastModifiedDate @Field("updated_at")
    var updatedAt: LocalDateTime? = null
)