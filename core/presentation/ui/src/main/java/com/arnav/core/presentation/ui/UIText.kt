package com.arnav.core.presentation.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UIText {
    data class DynamicString(val value: String): UIText
    data class StringRes(
        @androidx.annotation.StringRes val resId: Int,
        val args: Array<Any> = arrayOf()
    ): UIText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringRes -> stringResource(resId, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringRes -> context.getString(resId, *args)
        }
    }
}