package com.junka.presta.data.remote

import com.junka.data.ScoreRemoteDataSource
import com.junka.domain.Resource
import com.junka.domain.Score
import com.junka.presta.common.tryCall
import com.junka.presta.data.remote.mapper.toDomain
import javax.inject.Inject

class ScoreRemoteDataSourceImp @Inject constructor(
    private val scoreService: ScoreService
) : ScoreRemoteDataSource{
    override suspend fun getScore(dni: Int) : Resource<Score> = tryCall {
        scoreService.score(dni).toDomain()
    }.fold(
        ifLeft = { Resource.Failure(data = null, error = it) },
        ifRight = { Resource.Success(it) }
    )

}