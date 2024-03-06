package com.junka.presta.feature.customer.data

import com.junka.presta.core.model.Customer
import com.junka.presta.core.common.Resource
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val remoteDataSource: CustomerDataSource
) {

    suspend fun getCustomers() = remoteDataSource.getCustomers()

    suspend fun getCustomer(id: String) = remoteDataSource.getCustomer(id)

    suspend fun save(customer: Customer) = remoteDataSource.save(customer)

    suspend fun saveOrUpdate(customer: Customer) : Resource<Customer> {
        return if (customer.id != ""){
            update(customer)
        }else{
            save(customer)
        }
    }

    suspend fun delete(customer: Customer): Resource<Unit> = remoteDataSource.delete(customer)

    suspend fun update(customer: Customer): Resource<Customer> = remoteDataSource.update(customer)

}