package com.junka.data

import com.junka.domain.Resource
import com.junka.domain.Loan
import javax.inject.Inject

class LoanRepository @Inject constructor(
    private val remoteDataSource: LoanDataSource
) {

    suspend fun getLoans() = remoteDataSource.getLoans()

    suspend fun getLoan(id: String) = remoteDataSource.getLoan(id)

    suspend fun save(loan: Loan) = remoteDataSource.save(loan)

    suspend fun scoreAndSave(loan: Loan): Resource<Loan> {
        val result = remoteDataSource.score(loan)
        return when (result) {
            is Resource.Error -> result
            is Resource.Success -> {
                result.data.let { data ->
                    if (data.id != "") {
                        update(result.data)
                    } else {
                        save(result.data)
                    }
                }
            }
        }
    }

    suspend fun delete(loan: Loan): Resource<Boolean> = remoteDataSource.delete(loan)

    suspend fun update(loan: Loan): Resource<Loan> = remoteDataSource.update(loan)

}