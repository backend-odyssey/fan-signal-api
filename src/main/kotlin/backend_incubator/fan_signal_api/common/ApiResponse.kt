package backend_incubator.fan_signal_api.common

import com.fasterxml.jackson.annotation.JsonInclude

// 공통 응답 클래스 정의
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponse<T>(
    val status: Int,
    val message: String,
    val data: T? = null
) {
    companion object {
        private const val SUCCESS_STATUS = 200
        private const val SUCCESS_NO_CONTENT_STATUS = 204
        private const val SUCCESS_MESSAGE = "success"

        fun success(): ApiResponse<Void> =
            ApiResponse(SUCCESS_NO_CONTENT_STATUS, SUCCESS_MESSAGE)

        fun <T> success(data: T): ApiResponse<T> =
            ApiResponse(SUCCESS_STATUS, SUCCESS_MESSAGE, data)
    }
}