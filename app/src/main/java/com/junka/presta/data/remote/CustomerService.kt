package com.junka.presta.data.remote

import com.junka.presta.data.remote.model.SaveCustomerResponse
import com.junka.presta.data.remote.model.Customer
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Body

interface CustomerService {

    @GET("users.json")
    suspend fun getCustomers() : Map<String, Customer>

    @GET("users/{id}.json")
    suspend fun getCustomer(@Path("id") id : String) : Customer?

    @PUT("users/{id}.json")
    suspend fun update(@Path("id") id : String, @Body customer : Customer) : Customer

    @DELETE("users/{id}.json")
    suspend fun delete(@Path("id") id : String)

    @POST("users.json")
    suspend fun save(@Body customer : Customer) : SaveCustomerResponse
}