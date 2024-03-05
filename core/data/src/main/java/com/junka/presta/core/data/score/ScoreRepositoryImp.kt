package com.junka.presta.core.data.score

import com.junka.domain.Resource
import com.junka.domain.Score
import com.junka.presta.core.network.score.ScoreRemoteDataSource
import javax.inject.Inject

internal class ScoreRepositoryImp @Inject constructor(
    private val scoreRemoteDataSource : ScoreRemoteDataSource
) : ScoreRepository {

    override suspend fun getScore(dni : Int) : Resource<Score> = scoreRemoteDataSource.getScore(dni)
}