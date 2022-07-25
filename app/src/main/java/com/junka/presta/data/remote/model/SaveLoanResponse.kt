package com.junka.presta.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class SaveLoanResponse(
    @Json(name = "name")
    val name: String
)