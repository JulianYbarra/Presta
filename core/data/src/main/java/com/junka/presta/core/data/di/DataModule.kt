package com.junka.presta.core.data.di

import com.junka.presta.core.data.score.ScoreRepository
import com.junka.presta.core.data.score.ScoreRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindScoreRepository(scoreRepositoryImp: ScoreRepositoryImp) : ScoreRepository
}