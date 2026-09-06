package org.odin.cinekmp.core.common.mvi.uieffect

import kotlinx.coroutines.CoroutineDispatcher

abstract class UiEffectModifier {
    internal var enableLoading: suspend () -> Boolean = { true }
    internal var dispatcher: CoroutineDispatcher? = null
    internal var exceptionHandler: UiEffectExceptionHandler? = null
    internal var key: String? = null
    internal var onLoadingChanged: ((updateLoading: Boolean, isLoading: Boolean, key: String) -> Unit)? = null
}