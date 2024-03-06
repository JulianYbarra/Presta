package com.junka.presta.feature.customer.data

import com.junka.presta.core.model.Customer
import com.junka.presta.core.common.Resource

interface CustomerDataSource {
    suspend fun getCustomers() : Resource<List<Customer>>
    suspend fun getCustomer(id: String): Resource<Customer?>
    suspend fun save(customer : Customer) : Resource<Customer>
    suspend fun delete(customer : Customer) : Resource<Unit>
    suspend fun update(customer : Customer) : Resource<Customer>
}