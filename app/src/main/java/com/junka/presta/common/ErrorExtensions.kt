package com.junka.presta.common

import android.content.Context
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import retrofit2.HttpException
import java.io.IOException
import com.junka.domain.Error
import com.junka.presta.R

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}

fun Error.errorToString(context : Context) = when (this) {
    Error.Connectivity -> context.getString(R.string.connectivity_error)
    is Error.Server -> context.getString(R.string.server_error) + code
    is Error.Unknown -> context.getString(R.string.unknown_error) + message
}