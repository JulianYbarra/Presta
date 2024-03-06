package com.junka.presta.feature.customer.domain

import com.junka.presta.feature.customer.data.CustomerRepository
import javax.inject.Inject

class GetCustomerByIdUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
){

    suspend operator fun invoke(id : String) = customerRepository.getCustomer(id)
}