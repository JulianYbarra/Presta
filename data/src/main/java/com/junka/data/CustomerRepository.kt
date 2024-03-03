package com.junka.data

import com.junka.domain.Resource
import com.junka.domain.Customer
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val remoteDataSource: CustomerDataSource
) {

    suspend fun getCustomers() = remoteDataSource.getCustomers()

    suspend fun getCustomer(id: String) = remoteDataSource.getCustomer(id)

    suspend fun save(customer: Customer) = remoteDataSource.save(customer)

    suspend fun saveOrUpdate(customer: Customer) : Resource<Customer>{
        return if (customer.id != ""){
            update(customer)
        }else{
            save(customer)
        }
    }

    suspend fun delete(customer: Customer): Resource<Boolean> = remoteDataSource.delete(customer)

    suspend fun update(customer: Customer): Resource<Customer> = remoteDataSource.update(customer)

}