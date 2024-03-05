package com.junka.presta.common

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.junka.domain.ScoreStatus
import com.junka.presta.R

fun View.setColor(status: ScoreStatus?) {
    setBackgroundColor(context.statusColor(status))
}

fun TextView.setTextColor(status: ScoreStatus?) {
    setTextColor(context.statusColor(status))
}

fun LottieAnimationView.setAnimation(
    status: ScoreStatus?,
    autoStart: Boolean?
){
    isVisible = status != null

    if(autoStart == true && visibility == View.GONE) return

    val rawId = when (status) {
        ScoreStatus.APPROVE -> R.raw.approved
        ScoreStatus.REJECTED -> R.raw.disapproved
        ScoreStatus.ERROR -> R.raw.disapproved
        else -> null
    }

    if(rawId != null) setAnimation(rawId)
    if(autoStart != null && autoStart) playAnimation()
}