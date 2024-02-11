package com.junka.usecases

import com.junka.data.CustomerRepository
import javax.inject.Inject

class GetCustomersUseCase @Inject constructor(
    private val customerRepository : CustomerRepository
) {

    suspend operator fun invoke() = customerRepository.getCustomers()
}