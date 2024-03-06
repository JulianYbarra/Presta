package com.junka.presta.feature.customer.data.remote.model

import com.squareup.moshi.Json

data class SaveCustomerResponse(
    @Json(name = "name")
    val name: String
)