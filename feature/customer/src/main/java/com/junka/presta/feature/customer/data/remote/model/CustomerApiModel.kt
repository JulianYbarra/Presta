package com.junka.presta.feature.customer.data.remote.model

import com.junka.presta.core.model.Customer
import com.junka.presta.core.model.Score

data class CustomerApiModel(
    val name: String = "",
    val lastName: String = "",
    val dni: Int = 0,
    val score : com.junka.presta.core.model.Score? = null
)

fun CustomerApiModel.toDomainModel(id: String) = com.junka.presta.core.model.Customer(
    id,
    name,
    lastName,
    dni,
    score
)

fun com.junka.presta.core.model.Customer.fromDomainModel() = CustomerApiModel(name, lastName, dni, score)