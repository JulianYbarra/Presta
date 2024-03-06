package com.junka.presta.core.ui.extensions

import android.content.Context
import android.view.View
import android.widget.TextView
import com.junka.presta.core.designsystem.R
import com.junka.presta.core.model.ScoreStatus
import com.junka.presta.core.model.ScoreStatus.*

fun Context.statusColor(status: ScoreStatus?) = when (status) {
    ERROR -> getColor(R.color.md_theme_tertiary)
    APPROVE -> getColor(R.color.md_theme_primary)
    REJECTED -> getColor(R.color.md_theme_error)
    else -> getColor(R.color.md_theme_background)
}

fun View.setColor(status: ScoreStatus?) {
    setBackgroundColor(context.statusColor(status))
}

fun TextView.setTextColor(status: ScoreStatus?) {
    setTextColor(context.statusColor(status))
}