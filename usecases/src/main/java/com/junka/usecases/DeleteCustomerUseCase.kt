package com.junka.usecases

import com.junka.data.CustomerRepository
import com.junka.domain.Customer
import javax.inject.Inject

class DeleteCustomerUseCase @Inject constructor(
    private val customerRepository: CustomerRepository,
) {

    suspend operator fun invoke(customer : Customer) = customerRepository.delete(customer)
}