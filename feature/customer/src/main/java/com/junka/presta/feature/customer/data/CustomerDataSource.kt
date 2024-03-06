package com.junka.presta.feature.customer.data

import com.junka.presta.core.model.Customer
import com.junka.presta.core.common.Resource

interface CustomerDataSource {
    suspend fun getCustomers() : Resource<List<com.junka.presta.core.model.Customer>>
    suspend fun getCustomer(id: String): Resource<com.junka.presta.core.model.Customer?>
    suspend fun save(customer : com.junka.presta.core.model.Customer) : Resource<com.junka.presta.core.model.Customer>
    suspend fun delete(customer : com.junka.presta.core.model.Customer) : Resource<Unit>
    suspend fun update(customer : com.junka.presta.core.model.Customer) : Resource<com.junka.presta.core.model.Customer>
}