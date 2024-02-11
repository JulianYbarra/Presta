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

    suspend fun scoreAndSave(customer: Customer): Resource<Customer> {
        val result = remoteDataSource.score(customer)
        return when (result) {
            is Resource.Error -> result
            is Resource.Success -> {
                result.data.let { data ->
                    if (data.id != "") {
                        update(result.data)
                    } else {
                        save(result.data)
                    }
                }
            }
        }
    }

    suspend fun delete(customer: Customer): Resource<Boolean> = remoteDataSource.delete(customer)

    suspend fun update(customer: Customer): Resource<Customer> = remoteDataSource.update(customer)

}