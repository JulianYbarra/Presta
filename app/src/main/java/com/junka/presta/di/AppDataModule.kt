package com.junka.presta.di

import com.junka.data.LoanDataSource
import com.junka.presta.data.remote.LoanRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLoanDataSource(loanRemoteDataSource: LoanRemoteDataSource) : LoanDataSource
}