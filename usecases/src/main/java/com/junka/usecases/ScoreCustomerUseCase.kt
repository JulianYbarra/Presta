package com.junka.usecases

import com.junka.data.CustomerRepository
import com.junka.domain.Customer
import com.junka.domain.Resource
import javax.inject.Inject

class ScoreCustomerUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
){
    suspend operator fun invoke(customer : Customer) : Resource<Customer> {
        val result = customerRepository.score(customer)
        return when (result) {
            is Resource.Error -> result
            is Resource.Success -> {
                customerRepository.saveOrUpdate(result.data)
            }
        }
    }
}