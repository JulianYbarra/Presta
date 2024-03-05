package com.junka.presta.core.domain

import com.junka.data.CustomerRepository
import com.junka.domain.Customer
import com.junka.domain.Error
import com.junka.domain.Resource
import javax.inject.Inject

class ScoreCustomerUseCase @Inject constructor(
    private val customerRepository: CustomerRepository,
    private val scoreUseCase: ScoreUseCase
){
    suspend operator fun invoke(customer : Customer) : Resource<Customer> {
        val result = scoreUseCase(customer.dni)
        return when (result) {
            is Resource.Failure -> Resource.Failure(data = customer, error = Error.Unknown("can get customer score"))
            is Resource.Success -> {
                customerRepository.saveOrUpdate(customer.copy(score = result.data))
            }
        }
    }
}