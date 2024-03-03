package com.junka.domain

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val data: T? = null, val error: Error) : Resource<T>()
    data class Loading<T>(val data: T, val loading: Boolean)
}