package org.odin.cinekmp.core.common.text

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {

    data class DynamicString(val value: String) : UiText

    class ResourceString(
        val resource: StringResource,
        val args: List<Any> = emptyList()
    ) : UiText

    companion object {
        fun of(value: String): UiText = DynamicString(value)

        fun of(resource: StringResource, vararg args: Any): UiText =
            ResourceString(resource, args.toList())
    }
}

@Composable
fun UiText.asString(): String = when (this) {
    is UiText.DynamicString -> value
    is UiText.ResourceString -> stringResource(resource, *args.toTypedArray())
}