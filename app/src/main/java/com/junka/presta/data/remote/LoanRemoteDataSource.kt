package com.junka.presta.data.remote

import com.junka.data.LoanDataSource
import com.junka.domain.Resource
import com.junka.domain.Loan
import com.junka.presta.common.tryCall
import com.junka.presta.data.remote.model.fromDomainModel
import com.junka.presta.data.remote.model.toDomainModel
import javax.inject.Inject

class LoanRemoteDataSource @Inject constructor(
    private val monService: MonService,
    private val scoreService: ScoreService
) : LoanDataSource {

    override suspend fun getLoans(): Resource<List<Loan>> = tryCall {
        monService.getLoans().mapNotNull { map ->
            map.value.toDomainModel(map.key)
        }
    }.fold(
        ifLeft = { Resource.Error(error = it, data = emptyList()) },
        ifRight = { Resource.Success(data = it) })

    override suspend fun getLoan(id: String): Resource<Loan?> = tryCall {
        monService.getLoan(id)?.toDomainModel(id)
    }.fold(
        ifLeft = { Resource.Error(error = it, data = null) },
        ifRight = { Resource.Success(it) }
    )

    override suspend fun score(loan: Loan): Resource<Loan> = tryCall {
        scoreService.score(loan.dni)
    }.fold(
        ifLeft = { Resource.Error(error = it, data = loan) },
        ifRight = {
            Resource.Success(data = loan.copy(status = Loan.Status.valueOf(it.status.uppercase())))
        }
    )

    override suspend fun save(loan: Loan): Resource<Loan> = tryCall {
        monService.postLoan(loan.fromDomainModel())
    }.fold(
        ifLeft = { Resource.Error(error = it, data = loan) },
        ifRight = { Resource.Success(data = loan.copy(id = it.name)) }
    )

    override suspend fun delete(loan: Loan): Resource<Boolean> = tryCall {
        monService.deleteLoan(loan.id)
    }.fold(
        ifLeft = { Resource.Error(error = it, data = false) },
        ifRight = { Resource.Success(data = true) }
    )

    override suspend fun update(loan: Loan): Resource<Loan> = tryCall {
        monService.putLoan(loan.id, loan.fromDomainModel())
    }.fold(
        ifLeft = { Resource.Error(error = it, data = loan) },
        ifRight = { Resource.Success(data = it.toDomainModel(loan.id))}
    )
}