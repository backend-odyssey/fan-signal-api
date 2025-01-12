package backend_odyssey.fan_signal_api.exception

class CustomException(val errorCode: ErrorCode) : RuntimeException(errorCode.message) {
}