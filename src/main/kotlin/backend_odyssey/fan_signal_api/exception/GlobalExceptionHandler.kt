package backend_odyssey.fan_signal_api.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.reactive.resource.NoResourceFoundException
import org.springframework.web.server.ServerWebInputException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(ex: CustomException): ResponseEntity<ErrorResponse> {
        logger.error("CustomException occurred: ${ex.message}", ex)
        val errorCode = ex.errorCode
        val errorResponse = ErrorResponse(
            status = errorCode.status,
            message = errorCode.message
        )
        return ResponseEntity.status(errorCode.status).body(errorResponse)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNotFoundException(ex: NoResourceFoundException): ResponseEntity<ErrorResponse> {
        logger.error("NoResourceFoundException occurred: ${ex.message}", ex)
        val errorResponse = ErrorResponse(
            status = ErrorCode.NOT_FOUND.status,
            message = ErrorCode.NOT_FOUND.message
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        logger.error("IllegalArgumentException occurred: ${ex.message}", ex)
        val errorResponse = ErrorResponse(
            status = ErrorCode.BAD_REQUEST.status,
            message = ErrorCode.BAD_REQUEST.message
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(ServerWebInputException::class)
    fun handleValidationExceptions(ex: ServerWebInputException): ResponseEntity<ErrorResponse> {
        logger.error("ServerWebInputException occurred: ${ex.message}", ex)
        val errorResponse = ErrorResponse(
            status = ErrorCode.BAD_REQUEST.status,
            message = ErrorCode.BAD_REQUEST.message
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error("Exception occurred: ${ex.message}", ex)
        val errorResponse = ErrorResponse(
            status = ErrorCode.INTERNAL_SERVER_ERROR.status,
            message = ErrorCode.INTERNAL_SERVER_ERROR.message
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}

data class ErrorResponse(
    val status: Int,
    val message: String
)
