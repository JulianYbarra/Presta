package com.junka.presta.feature.customer.domain

import com.junka.presta.feature.customer.data.CustomerRepository
import com.junka.presta.core.model.Customer
import com.junka.presta.core.common.Error
import com.junka.presta.core.common.Resource
import com.junka.presta.core.domain.ScoreUseCase
import javax.inject.Inject

class ScoreCustomerUseCase @Inject constructor(
    private val customerRepository: CustomerRepository,
    private val scoreUseCase: ScoreUseCase
){
    suspend operator fun invoke(customer : Customer) : Resource<Customer> {
        val result = scoreUseCase(customer.dni)
        return when (result) {
            is Resource.Success -> {
                customerRepository.saveOrUpdate(customer.copy(score = result.data))
            }
            else -> Resource.Failure(Error.Unknown("can get customer score"))
        }
    }
}