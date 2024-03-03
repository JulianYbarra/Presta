package com.junka.presta.data.remote

import com.junka.data.CustomerDataSource
import com.junka.domain.Resource
import com.junka.domain.Customer
import com.junka.presta.common.tryCall
import com.junka.presta.data.remote.model.fromDomainModel
import com.junka.presta.data.remote.model.toDomainModel
import javax.inject.Inject

class CustomerRemoteDataSource @Inject constructor(
    private val customerService: CustomerService
) : CustomerDataSource {

    override suspend fun getCustomers(): Resource<List<Customer>> = tryCall {
        customerService.getCustomers().mapNotNull { map ->
            map.value.toDomainModel(map.key)
        }
    }.fold(
        ifLeft = { Resource.Failure(error = it, data = emptyList()) },
        ifRight = { Resource.Success(data = it) })

    override suspend fun getCustomer(id: String): Resource<Customer?> = tryCall {
        customerService.getCustomer(id)?.toDomainModel(id)
    }.fold(
        ifLeft = { Resource.Failure(error = it, data = null) },
        ifRight = { Resource.Success(it) }
    )

    override suspend fun save(customer: Customer): Resource<Customer> = tryCall {
        customerService.save(customer.fromDomainModel())
    }.fold(
        ifLeft = { Resource.Failure(error = it, data = customer) },
        ifRight = { Resource.Success(data = customer.copy(id = it.name)) }
    )

    override suspend fun delete(customer: Customer): Resource<Boolean> = tryCall {
        customerService.delete(customer.id)
    }.fold(
        ifLeft = { Resource.Failure(error = it, data = false) },
        ifRight = { Resource.Success(data = true) }
    )

    override suspend fun update(customer: Customer): Resource<Customer> = tryCall {
        customerService.update(customer.id, customer.fromDomainModel())
    }.fold(
        ifLeft = { Resource.Failure(error = it, data = customer) },
        ifRight = { Resource.Success(data = it.toDomainModel(customer.id))}
    )
}