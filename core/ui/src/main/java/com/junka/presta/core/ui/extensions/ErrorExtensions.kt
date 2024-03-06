package com.junka.presta.core.ui.extensions

import android.content.Context
import com.junka.presta.core.common.Error
import com.junka.presta.core.designsystem.R

fun Error.errorToString(context : Context) = when (this) {
    Error.Connectivity -> context.getString(R.string.connectivity_error)
    is Error.Server -> context.getString(R.string.server_error) + code
    is Error.Unknown -> context.getString(R.string.unknown_error) + message
}