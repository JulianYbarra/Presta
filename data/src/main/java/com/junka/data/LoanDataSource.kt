package com.junka.data

import com.junka.domain.Resource
import com.junka.domain.Loan

interface LoanDataSource {
    suspend fun getLoans() : Resource<List<Loan>>
    suspend fun getLoan(id: String): Resource<Loan?>
    suspend fun save(loan : Loan) : Resource<Loan>
    suspend fun score(loan : Loan) : Resource<Loan>
    suspend fun delete(loan : Loan) : Resource<Boolean>
    suspend fun update(loan : Loan) : Resource<Loan>
}