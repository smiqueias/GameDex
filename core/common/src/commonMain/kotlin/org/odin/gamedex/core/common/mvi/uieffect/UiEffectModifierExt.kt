package org.odin.gamedex.core.common.mvi.uieffect


import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher

fun UiEffectModifier.loading(enabled: suspend () -> Boolean) = apply {
    enableLoading = enabled
}

fun UiEffectModifier.dispatcher(dispatcher: CoroutineDispatcher) = apply {
    this.dispatcher = dispatcher
}

fun UiEffectModifier.exceptionHandler(
    handler: UiEffectExceptionHandler.(CoroutineContext, Throwable) -> Unit
) = apply {
    this.exceptionHandler = UiEffectExceptionHandler.coroutineExceptionHandler(handler)
}

fun UiEffectModifier.key(key: String) = apply {
    this.key = key
}

fun UiEffectModifier.onLoadingChanged(
    onLoadingChanged: (updateLoading: Boolean, isLoading: Boolean, key: String) -> Unit
) = apply {
    this.onLoadingChanged = onLoadingChanged
}