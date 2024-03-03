package com.junka.presta.common

import android.content.Context
import com.junka.domain.ScoreStatus
import com.junka.domain.ScoreStatus.*
import com.junka.presta.R

fun Context.statusColor(status: ScoreStatus?) = when (status) {
    ERROR -> getColor(R.color.md_theme_tertiary)
    APPROVE -> getColor(R.color.md_theme_primary)
    REJECTED -> getColor(R.color.md_theme_error)
    else -> getColor(R.color.md_theme_background)
}