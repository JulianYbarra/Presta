package com.junka.presta.core.testing.repository

import com.junka.domain.Resource
import com.junka.presta.core.model.Score
import com.junka.presta.core.data.score.ScoreRepository

class ScoreRepositoryFake(
    val score: com.junka.presta.core.model.Score
) : ScoreRepository {
    override suspend fun getScore(dni: Int): Resource<com.junka.presta.core.model.Score>{
        return Resource.Success(score)
    }


}