package com.junka.presta.data.remote

import com.junka.presta.data.remote.model.SaveLoanResponse
import com.junka.presta.data.remote.model.Loan
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Body

interface LoanService {

    @GET("users.json")
    suspend fun getLoans() : Map<String, Loan>

    @GET("users/{id}.json")
    suspend fun getLoan(@Path("id") id : String) : Loan?

    @PUT("users/{id}.json")
    suspend fun putLoan(@Path("id") id : String, @Body user : Loan) : Loan

    @DELETE("users/{id}.json")
    suspend fun deleteLoan(@Path("id") id : String)

    @POST("users.json")
    suspend fun postLoan(@Body user : Loan) : SaveLoanResponse
}