package com.junka.presta.core.network.score

import com.junka.presta.core.model.Score
import com.junka.presta.core.common.Resource

interface ScoreRemoteDataSource {
    suspend fun getScore(dni : Int) : Resource<com.junka.presta.core.model.Score>
}