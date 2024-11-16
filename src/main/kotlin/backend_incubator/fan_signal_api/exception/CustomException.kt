package backend_incubator.fan_signal_api.exception

class CustomException(val errorCode: ErrorCode) : RuntimeException(errorCode.message) {
}