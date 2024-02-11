package com.junka.usecases

import com.junka.data.CustomerRepository
import javax.inject.Inject

class GetCustomerByIdUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
){

    suspend operator fun invoke(id : String) = customerRepository.getCustomer(id)
}