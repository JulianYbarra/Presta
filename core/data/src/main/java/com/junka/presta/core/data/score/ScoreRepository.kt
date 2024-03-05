package com.junka.presta.core.data.score

import com.junka.domain.Resource
import com.junka.domain.Score

interface ScoreRepository {

    suspend fun getScore(dni : Int) : Resource<Score>
}