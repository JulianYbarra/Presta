package com.junka.presta.data.remote

import com.junka.presta.data.remote.model.ScoreResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ScoreService {

    @GET("api/v4/scoring/pre-score/{dni}")
    suspend fun score(@Path("dni") dni : Int) : ScoreResponse
}