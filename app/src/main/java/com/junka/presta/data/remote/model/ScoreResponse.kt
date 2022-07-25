package com.junka.presta.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ScoreResponse(
    @Json(name = "has_error")
    val hasError: Boolean,
    @Json(name = "status")
    val status: String
)