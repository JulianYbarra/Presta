package com.junka.presta.data.remote.model

import com.junka.domain.Loan as DomainModel

data class Loan(
    val name: String = "",
    val lastName: String = "",
    val dni: Int = 0,
    val status: String = ""
)

fun Loan.toDomainModel(id: String) = DomainModel(
    id,
    name,
    lastName,
    dni,
    DomainModel.Status.valueOf(status.uppercase())
)

fun DomainModel.fromDomainModel() = Loan(name, lastName, dni, status.value)
