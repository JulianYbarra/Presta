package com.junka.presta.feature.customer.di

import com.junka.presta.feature.customer.data.CustomerDataSource
import com.junka.presta.feature.customer.data.remote.CustomerRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CustomerDataModule {

    @Binds
    internal abstract fun bindCustomerDataSource(customerRemoteDataSource: CustomerRemoteDataSource): CustomerDataSource

}