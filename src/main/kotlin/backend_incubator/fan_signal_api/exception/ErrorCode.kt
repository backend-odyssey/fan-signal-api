package backend_incubator.fan_signal_api.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: Int, val message: String) {
    // Common
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An internal server error has occurred."),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "The requested resource could not be found"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Invalid request parameter."),

    // POST
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "Post not found with id: {id}")

}