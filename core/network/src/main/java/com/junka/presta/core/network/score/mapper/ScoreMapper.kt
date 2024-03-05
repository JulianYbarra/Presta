package com.junka.presta.core.network.score.mapper

import com.junka.domain.Score
import com.junka.presta.core.network.score.model.ScoreApiModel
import java.util.Date

internal fun ScoreApiModel.toDomain() = Score(
    time = Date().time,
    status = status
)