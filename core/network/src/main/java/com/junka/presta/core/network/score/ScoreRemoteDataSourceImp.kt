package com.junka.presta.core.network.score


import com.junka.presta.core.model.Score
import com.junka.presta.core.common.Resource
import com.junka.presta.core.common.tryCall

import com.junka.presta.core.network.score.mapper.toDomain
import javax.inject.Inject

internal class ScoreRemoteDataSourceImp @Inject constructor(
    private val scoreService: ScoreService
) : ScoreRemoteDataSource {
    override suspend fun getScore(dni: Int) : Resource<com.junka.presta.core.model.Score> =  tryCall {
        scoreService.score(dni).toDomain()
    }

}