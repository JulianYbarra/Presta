package com.junka.presta.core.network.score.mapper

import com.junka.presta.core.model.Score
import com.junka.presta.core.network.score.model.ScoreApiModel
import java.util.Date

internal fun ScoreApiModel.toDomain() = com.junka.presta.core.model.Score(
    time = Date().time,
    status = status
)