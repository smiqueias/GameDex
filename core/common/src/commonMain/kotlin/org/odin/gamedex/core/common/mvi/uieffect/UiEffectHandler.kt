package org.odin.gamedex.core.common.mvi.uieffect

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


interface UiEffectHandler {
    val scope: CoroutineScope
    val uiEffect: Flow<UiEffect>
    fun emitUiEffect(effect: UiEffect)
    fun exceptionHandler(exception: Throwable, retry: (() -> Unit)? = null)
}