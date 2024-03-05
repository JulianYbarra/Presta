package com.junka.presta.core.testing.repository

import com.junka.domain.Resource
import com.junka.domain.Score
import com.junka.presta.core.data.score.ScoreRepository

class ScoreRepositoryFake(
    val score: Score
) : ScoreRepository {
    override suspend fun getScore(dni: Int): Resource<Score>{
        return Resource.Success(score)
    }


}