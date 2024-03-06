package com.junka.presta.feature.customer.di

import com.junka.presta.feature.customer.BuildConfig
import com.junka.presta.feature.customer.data.remote.CustomerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CustomerRemoteModule {

    @Provides
    @Singleton
    @ApiUrl
    internal fun provideApiUrl(): String = BuildConfig.URL_REALTIME

    @Provides
    @Singleton
    internal fun provideRemoteService(
        @ApiUrl apiUrl: String,
        okHttpClient: OkHttpClient
    ): CustomerService {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}