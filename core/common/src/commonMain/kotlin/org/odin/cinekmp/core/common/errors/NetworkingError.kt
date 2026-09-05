package org.odin.cinekmp.core.common.errors

sealed class NetworkingError(message: String, cause: Throwable? = null) : Exception(message, cause) {

    data object NoConnection : NetworkingError("Sem conexão com a internet")

    data object Timeout : NetworkingError("A requisição demorou demais para responder")

    data class ServerError(
        val code: Int,
        val serverMessage: String? = null
    ) : NetworkingError("Erro do servidor: $code")

    data class SerializationError(
        override val cause: Throwable
    ) : NetworkingError("Falha ao interpretar a resposta do servidor", cause)

    data class Unknown(
        override val cause: Throwable
    ) : NetworkingError("Erro inesperado", cause)
}