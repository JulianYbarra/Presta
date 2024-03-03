package com.junka.data

import com.junka.domain.Resource
import com.junka.domain.Score
interface ScoreRemoteDataSource {
    suspend fun getScore(dni : Int) : Resource<Score>
}