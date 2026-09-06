package org.odin.cinekmp.core.common.mvi.uieffect

import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.odin.cinekmp.core.common.text.UiText

fun UiEffectHandler.disableLoading() {
    emitUiEffect(UiEffect.Loading(false))
}

fun UiEffectHandler.enableLoading() {
    emitUiEffect(UiEffect.Loading(true))
}

fun UiEffectHandler.feedbackError(
    message: UiText,
    duration: Duration = UiEffect.Feedback.Kind.Error.defaultDuration
) {
    emitUiEffect(UiEffect.Feedback.error(message, duration))
}

fun UiEffectHandler.feedbackInfo(
    message: UiText,
    duration: Duration = UiEffect.Feedback.Kind.Info.defaultDuration
) {
    emitUiEffect(UiEffect.Feedback.info(message, duration))
}

fun UiEffectHandler.feedbackWarning(
    message: UiText,
    duration: Duration = UiEffect.Feedback.Kind.Warning.defaultDuration
) {
    emitUiEffect(UiEffect.Feedback.warning(message, duration))
}

fun UiEffectHandler.feedbackSuccess(
    message: UiText,
    duration: Duration = UiEffect.Feedback.Kind.Success.defaultDuration
) {
    emitUiEffect(UiEffect.Feedback.success(message, duration))
}

val UiEffectHandler.Modifier: UiEffectModifier
    get() = object : UiEffectModifier() {}

fun UiEffectHandler.launchWithUiEffect(
    modifier: UiEffectModifier = Modifier,
    action: suspend () -> Unit
): Job {
    val dispatcher = modifier.dispatcher ?: Dispatchers.IO
    val exceptionHandler = modifier.exceptionHandler ?: uiEffectExceptionHandler

    return scope.launch(dispatcher + exceptionHandler) {
        val enableLoading = modifier.enableLoading()
        val updateLoading: (Boolean) -> Unit = { isLoading ->
            modifier.onLoadingChanged?.invoke(enableLoading, isLoading, modifier.key ?: "")
                ?: emitUiEffect(UiEffect.Loading(isLoading))
        }
        try {
            if (enableLoading) updateLoading(true)
            action()
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (throwable: Throwable) {
            exceptionHandler.retry { launchWithUiEffect(modifier, action) }
            throw throwable
        } finally {
            updateLoading(false)
        }
    }
}