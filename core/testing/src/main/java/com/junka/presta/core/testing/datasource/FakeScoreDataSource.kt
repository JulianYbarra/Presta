package com.junka.presta.core.testing.datasource

import com.junka.domain.Error
import com.junka.domain.Resource
import com.junka.domain.Score
import com.junka.presta.core.network.score.ScoreRemoteDataSource

class FakeScoreDataSource(
    val score : Score? = null
) : ScoreRemoteDataSource {
    override suspend fun getScore(dni: Int): Resource<Score> {
        return if (score == null)
            Resource.Failure(null,Error.Unknown("score is null"))
        else
            Resource.Success(score)
    }
}