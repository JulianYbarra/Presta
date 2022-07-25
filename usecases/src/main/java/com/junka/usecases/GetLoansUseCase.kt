package com.junka.usecases

import com.junka.data.LoanRepository
import javax.inject.Inject

class GetLoansUseCase @Inject constructor(
    private val loanRepository : LoanRepository
) {

    suspend operator fun invoke() = loanRepository.getLoans()
}