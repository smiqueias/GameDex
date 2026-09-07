package org.odin.gamedex.core.common.mvi.uieffect

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object FeedbackDuration {
    val Short: Duration = 5.seconds
    val Long: Duration = 7.seconds
    val Indefinite: Duration = Duration.INFINITE
}