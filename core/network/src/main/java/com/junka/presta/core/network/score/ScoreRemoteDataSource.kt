package com.junka.presta.core.network.score

import com.junka.domain.Resource
import com.junka.domain.Score
interface ScoreRemoteDataSource {
    suspend fun getScore(dni : Int) : Resource<Score>
}