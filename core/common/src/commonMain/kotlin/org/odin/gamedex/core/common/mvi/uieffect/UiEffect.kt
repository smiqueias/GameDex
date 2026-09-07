package org.odin.gamedex.core.common.mvi.uieffect

import org.odin.gamedex.core.common.errors.NetworkingError
import org.odin.gamedex.core.common.text.UiText
import kotlin.time.Duration

interface UiEffect {

    data object Idle : UiEffect

    data class Loading(val isLoading: Boolean) : UiEffect

    data class Error(val error: NetworkingError, val retry: (() -> Unit)? = null) : UiEffect

    data class OpenDeeplink(val url: String) : UiEffect

    data class Feedback(
        val message: UiText,
        val kind: Kind,
        val title: UiText? = null,
        val duration: Duration = kind.defaultDuration
    ) : UiEffect {
        enum class Kind(val defaultDuration: Duration) {
            Info(FeedbackDuration.Long),
            Warning(FeedbackDuration.Long),
            Error(FeedbackDuration.Short),
            Success(FeedbackDuration.Short),
            Placeholder(FeedbackDuration.Short)
        }

        companion object {
            fun info(message: UiText, duration: Duration = Kind.Info.defaultDuration) = Feedback(message, Kind.Info, duration = duration)
            fun warning(message: UiText, duration: Duration = Kind.Warning.defaultDuration) = Feedback(message, Kind.Warning, duration = duration)
            fun error(message: UiText, duration: Duration = Kind.Error.defaultDuration) = Feedback(message, Kind.Error, duration = duration)
            fun success(message: UiText, duration: Duration = Kind.Success.defaultDuration) = Feedback(message, Kind.Success, duration = duration)
            fun placeholder(message: UiText, duration: Duration = Kind.Placeholder.defaultDuration) = Feedback(message, Kind.Placeholder, duration = duration)
        }
    }

    companion object {
        val UiEffect.isLoading: Boolean get() = ((this as? Loading)?.isLoading == true)
        val UiEffect.isError: Boolean get() = ((this as? Error) != null)
        val UiEffect.isFeedback: Boolean get() = ((this as? Feedback) != null)

    }
}