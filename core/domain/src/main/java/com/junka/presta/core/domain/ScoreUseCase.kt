package com.junka.presta.core.domain

import com.junka.presta.core.model.Score
import com.junka.presta.core.common.Resource
import com.junka.presta.core.data.score.ScoreRepository
import javax.inject.Inject

class ScoreUseCase @Inject constructor(
    private val scoreRepository: ScoreRepository
){
    suspend operator fun invoke(dni : Int): Resource<Score> =
        scoreRepository.getScore(dni)
}