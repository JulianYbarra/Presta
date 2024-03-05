package com.junka.presta.core.network.di

import com.junka.presta.core.network.score.ScoreRemoteDataSource
import com.junka.presta.core.network.score.ScoreRemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkDataModule {

    @Binds
    internal abstract fun bindScoreRemoteSource(scoreRemoteDataSourceImp: ScoreRemoteDataSourceImp) : ScoreRemoteDataSource
}