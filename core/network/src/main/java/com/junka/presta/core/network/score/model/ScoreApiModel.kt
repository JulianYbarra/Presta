package com.junka.presta.core.network.score.model


import com.squareup.moshi.Json

data class ScoreApiModel(
    @Json(name = "has_error")
    val hasError: Boolean,
    @Json(name = "status")
    val status: String
)