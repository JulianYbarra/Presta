package com.junka.presta.feature.customer.domain

import com.junka.presta.core.model.Customer
import com.junka.presta.feature.customer.data.CustomerRepository
import javax.inject.Inject

class DeleteCustomerUseCase @Inject constructor(
    private val customerRepository: CustomerRepository,
) {

    suspend operator fun invoke(customer : Customer) = customerRepository.delete(customer)
}