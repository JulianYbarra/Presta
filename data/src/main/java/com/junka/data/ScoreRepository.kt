package com.junka.data

import com.junka.domain.Resource
import com.junka.domain.Score
import javax.inject.Inject

class ScoreRepository @Inject constructor(
    private val scoreRemoteDataSource : ScoreRemoteDataSource
) {

    suspend fun getScore(dni : Int) : Resource<Score> = scoreRemoteDataSource.getScore(dni)
}