package com.junka.presta.feature.customer.data

import com.junka.presta.core.model.Customer
import com.junka.presta.core.common.Resource
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val remoteDataSource: CustomerDataSource
) {

    suspend fun getCustomers() = remoteDataSource.getCustomers()

    suspend fun getCustomer(id: String) = remoteDataSource.getCustomer(id)

    suspend fun save(customer: com.junka.presta.core.model.Customer) = remoteDataSource.save(customer)

    suspend fun saveOrUpdate(customer: com.junka.presta.core.model.Customer) : Resource<com.junka.presta.core.model.Customer> {
        return if (customer.id != ""){
            update(customer)
        }else{
            save(customer)
        }
    }

    suspend fun delete(customer: com.junka.presta.core.model.Customer): Resource<Unit> = remoteDataSource.delete(customer)

    suspend fun update(customer: com.junka.presta.core.model.Customer): Resource<com.junka.presta.core.model.Customer> = remoteDataSource.update(customer)

}