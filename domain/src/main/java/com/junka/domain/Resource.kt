package com.junka.domain

import com.junka.domain.Error as DError

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val data: T, val error: DError) : Resource<T>()
    data class Loading<T>(val data: T, val loading: Boolean)
}