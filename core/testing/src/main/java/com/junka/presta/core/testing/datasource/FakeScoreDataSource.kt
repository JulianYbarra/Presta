package com.junka.presta.core.testing.datasource

import com.junka.presta.core.common.Error
import com.junka.presta.core.common.Resource
import com.junka.presta.core.model.Score
import com.junka.presta.core.network.score.ScoreRemoteDataSource

class FakeScoreDataSource(
    val score : Score? = null
) : ScoreRemoteDataSource {
    override suspend fun getScore(dni: Int): Resource<Score> {
        return if (score == null)
            Resource.Failure(Error.Unknown("score is null"))
        else
            Resource.Success(score)
    }
}