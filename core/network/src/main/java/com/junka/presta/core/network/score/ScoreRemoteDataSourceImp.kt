package com.junka.presta.core.network.score

import com.junka.domain.Error
import com.junka.domain.Resource
import com.junka.domain.Score

import com.junka.presta.core.network.score.mapper.toDomain
import javax.inject.Inject

internal class ScoreRemoteDataSourceImp @Inject constructor(
    private val scoreService: ScoreService
) : ScoreRemoteDataSource {
    override suspend fun getScore(dni: Int) : Resource<Score> = try {
        Resource.Success(scoreService.score(dni).toDomain())
    } catch (e : Throwable){
        Resource.Failure(null, Error.Unknown("error"))
    }

}