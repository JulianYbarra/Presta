package com.junka.data

import com.junka.domain.Resource
import com.junka.domain.Customer

interface CustomerDataSource {
    suspend fun getCustomers() : Resource<List<Customer>>
    suspend fun getCustomer(id: String): Resource<Customer?>
    suspend fun save(customer : Customer) : Resource<Customer>
    suspend fun delete(customer : Customer) : Resource<Boolean>
    suspend fun update(customer : Customer) : Resource<Customer>
}