package backend_odyssey.fan_signal_api.post

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PostRepository : ReactiveMongoRepository<Post, String> {
}