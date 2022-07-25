package com.junka.usecases

import com.junka.data.LoanRepository
import com.junka.domain.Loan
import javax.inject.Inject

class ScoreLoanUseCase @Inject constructor(
    private val loanRepository: LoanRepository
){

    suspend operator fun invoke(loan : Loan) = loanRepository.scoreAndSave(loan)
}