package com.junka.presta.data.remote.model

import com.junka.domain.Customer as DomainModel

data class Customer(
    val name: String = "",
    val lastName: String = "",
    val dni: Int = 0,
    val status: String = ""
)

fun Customer.toDomainModel(id: String) = DomainModel(
    id,
    name,
    lastName,
    dni,
    DomainModel.Status.valueOf(status.uppercase())
)

fun DomainModel.fromDomainModel() = Customer(name, lastName, dni, status.value)