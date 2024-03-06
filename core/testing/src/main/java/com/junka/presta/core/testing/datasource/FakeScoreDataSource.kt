package com.junka.presta.core.testing.datasource

import com.junka.presta.core.common.Error
import com.junka.domain.Resource
import com.junka.presta.core.model.Score
import com.junka.presta.core.network.score.ScoreRemoteDataSource

class FakeScoreDataSource(
    val score : com.junka.presta.core.model.Score? = null
) : ScoreRemoteDataSource {
    override suspend fun getScore(dni: Int): Resource<com.junka.presta.core.model.Score> {
        return if (score == null)
            Resource.Failure(null, com.junka.presta.core.common.Error.Unknown("score is null"))
        else
            Resource.Success(score)
    }
}