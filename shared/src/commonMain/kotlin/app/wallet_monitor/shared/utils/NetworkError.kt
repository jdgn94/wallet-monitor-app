package app.wallet_monitor.shared.utils

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    NOT_FOUND,
    FORBIDDEN,
    UNAUTHORIZED,
    CONFLICT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN;
}