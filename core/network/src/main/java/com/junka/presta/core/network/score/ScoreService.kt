package com.junka.presta.core.network.score

import com.junka.presta.core.network.score.model.ScoreApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ScoreService {

    @GET("api/v4/scoring/pre-score/{dni}")
    suspend fun score(@Path("dni") dni : Int) : ScoreApiModel
}