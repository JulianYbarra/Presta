package com.junka.presta.feature.customer.data.remote

import com.junka.presta.core.model.Customer
import com.junka.presta.core.common.Resource
import com.junka.presta.core.common.tryCall
import com.junka.presta.feature.customer.data.CustomerDataSource
import com.junka.presta.feature.customer.data.remote.model.fromDomainModel
import com.junka.presta.feature.customer.data.remote.model.toDomainModel
import javax.inject.Inject

internal class CustomerRemoteDataSource @Inject constructor(
    private val customerService: CustomerService
) : CustomerDataSource {

    override suspend fun getCustomers(): Resource<List<Customer>> = tryCall {
        customerService.getCustomers().mapNotNull { map ->
            map.value.toDomainModel(map.key)
        }
    }

    override suspend fun getCustomer(id: String): Resource<Customer?> = tryCall {
        customerService.getCustomer(id)?.toDomainModel(id)
    }

    override suspend fun save(customer: Customer): Resource<Customer> = tryCall {
        val c = customerService.save(customer.fromDomainModel())
        customer.copy(id = c.name)
    }

    override suspend fun delete(customer: Customer): Resource<Unit> = tryCall {
        customerService.delete(customer.id)
    }

    override suspend fun update(customer: Customer): Resource<Customer> = tryCall {
        customerService.update(customer.id, customer.fromDomainModel())
            .toDomainModel(customer.id)
    }
}