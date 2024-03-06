package com.junka.presta.core.data.score

import com.junka.presta.core.model.Score
import com.junka.presta.core.common.Resource

interface ScoreRepository {

    suspend fun getScore(dni : Int) : Resource<com.junka.presta.core.model.Score>
}