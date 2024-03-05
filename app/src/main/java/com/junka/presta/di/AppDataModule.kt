package com.junka.presta.di

import com.junka.data.CustomerDataSource
import com.junka.presta.data.remote.CustomerRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindCustomerDataSource(customerRemoteDataSource: CustomerRemoteDataSource) : CustomerDataSource
}