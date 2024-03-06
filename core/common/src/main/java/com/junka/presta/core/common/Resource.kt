package com.junka.presta.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure(val error: Error) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}

fun <T> Flow<T>.asResource(): Flow<Resource<T>> = map<T, Resource<T>> { Resource.Success(it) }
    .onStart { emit(Resource.Loading) }
    .catch { emit(Resource.Failure(error = it.toError())) }

suspend fun <T> tryCall(action: suspend () -> T): Resource<T> = try {
    Resource.Success(action())
} catch (e: Exception) {
    Resource.Failure(e.toError())
}

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}