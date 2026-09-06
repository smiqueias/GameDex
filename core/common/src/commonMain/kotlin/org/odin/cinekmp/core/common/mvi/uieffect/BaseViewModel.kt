package org.odin.cinekmp.core.common.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.odin.cinekmp.core.common.errors.NetworkingError
import org.odin.cinekmp.core.common.mvi.uieffect.UiEffect
import org.odin.cinekmp.core.common.mvi.uieffect.UiEffectHandler
import org.odin.cinekmp.core.common.text.UiText

abstract class BaseViewModel<UiState, UiAction, SideEffect>(
    initialState: UiState,
) : ViewModel(), UiEffectHandler {

    override val scope: CoroutineScope = viewModelScope

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    protected val currentState: UiState get() = _uiState.value

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect: Flow<SideEffect> = _sideEffect.receiveAsFlow()

    private val _uiEffect = Channel<UiEffect>(Channel.BUFFERED)
    override val uiEffect: Flow<UiEffect> = _uiEffect.receiveAsFlow()

    fun onAction(action: UiAction) {
        handleAction(action)
    }

    protected abstract fun handleAction(action: UiAction)

    protected fun updateState(reduce: UiState.() -> UiState) {
        _uiState.update { it.reduce() }
    }

    protected fun sendEffect(effect: SideEffect) {
        viewModelScope.launch { _sideEffect.send(effect) }
    }

    override fun emitUiEffect(effect: UiEffect) {
        viewModelScope.launch { _uiEffect.send(effect) }
    }

    override fun exceptionHandler(exception: Throwable, retry: (() -> Unit)?) {
        val error = exception as? NetworkingError ?: NetworkingError.Unknown(exception)

        when (error) {
            is NetworkingError.NoConnection ->
                emitUiEffect(UiEffect.Feedback.error(UiText.of("Sem conexão. Verifique sua internet.")))

            is NetworkingError.Timeout ->
                emitUiEffect(UiEffect.Feedback.error(UiText.of("A requisição demorou demais. Tente novamente.")))

            is NetworkingError.ServerError ->
                emitUiEffect(UiEffect.Error(error, retry))

            is NetworkingError.SerializationError ->
                emitUiEffect(UiEffect.Feedback.error(UiText.of("Erro inesperado ao processar a resposta.")))

            is NetworkingError.Unknown ->
                emitUiEffect(UiEffect.Feedback.error(UiText.of("Ocorreu um erro inesperado. Tente novamente.")))
        }
    }
}