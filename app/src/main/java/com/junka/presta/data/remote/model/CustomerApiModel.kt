package com.junka.presta.data.remote.model

import com.junka.domain.Customer
import com.junka.domain.Score

data class CustomerApiModel(
    val name: String = "",
    val lastName: String = "",
    val dni: Int = 0,
    val score : Score? = null
)

fun CustomerApiModel.toDomainModel(id: String) = Customer(
    id,
    name,
    lastName,
    dni,
    score
)

fun Customer.fromDomainModel() = CustomerApiModel(name, lastName, dni, score)