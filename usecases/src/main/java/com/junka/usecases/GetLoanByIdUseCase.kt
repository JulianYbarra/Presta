package com.junka.usecases

import com.junka.data.LoanRepository
import javax.inject.Inject

class GetLoanByIdUseCase @Inject constructor(
    private val loanRepository: LoanRepository
){

    suspend operator fun invoke(id : String) = loanRepository.getLoan(id)
}