package com.junka.presta.feature.customer.domain

import com.junka.presta.feature.customer.data.CustomerRepository
import javax.inject.Inject

class GetCustomersUseCase @Inject constructor(
    private val customerRepository : CustomerRepository
) {

    suspend operator fun invoke() = customerRepository.getCustomers()
}