package com.junka.presta.core.data.score

import com.junka.presta.core.model.Score
import com.junka.presta.core.common.Resource
import com.junka.presta.core.network.score.ScoreRemoteDataSource
import javax.inject.Inject

internal class ScoreRepositoryImp @Inject constructor(
    private val scoreRemoteDataSource: ScoreRemoteDataSource
) : ScoreRepository {

    override suspend fun getScore(dni: Int): Resource<com.junka.presta.core.model.Score> =
        scoreRemoteDataSource.getScore(dni)
}