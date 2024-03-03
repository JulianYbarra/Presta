package com.junka.presta.data.remote.mapper

import com.junka.domain.Score
import com.junka.presta.data.remote.model.ScoreApiModel
import java.util.Date

fun ScoreApiModel.toDomain() = Score(
    time = Date().time,
    status = status
)