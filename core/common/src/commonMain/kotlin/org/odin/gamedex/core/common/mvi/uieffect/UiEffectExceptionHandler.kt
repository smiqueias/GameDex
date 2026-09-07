package org.odin.gamedex.core.common.mvi.uieffect

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class UiEffectExceptionHandler(
    private val uiEffectHandler: UiEffectHandler?
) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    private var retry: () -> Unit = {}

    open fun retry(fn: () -> Unit) {
        retry = fn
    }

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        uiEffectHandler?.exceptionHandler(exception, retry)
    }

    companion object {
        val UiEffectHandler.uiEffectExceptionHandler: UiEffectExceptionHandler
            get() = object : UiEffectExceptionHandler(this@uiEffectExceptionHandler) {}

        fun coroutineExceptionHandler(
            handle: UiEffectExceptionHandler.(CoroutineContext, Throwable) -> Unit
        ): UiEffectExceptionHandler = object : UiEffectExceptionHandler(null) {
            override fun handleException(context: CoroutineContext, exception: Throwable) {
                handle(context, exception)
            }
        }
    }
}