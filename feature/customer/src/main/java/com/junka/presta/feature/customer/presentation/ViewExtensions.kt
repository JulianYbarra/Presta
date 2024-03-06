package com.junka.presta.feature.customer.presentation

import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.junka.presta.core.model.ScoreStatus
import com.junka.presta.core.designsystem.R

fun LottieAnimationView.setAnimation(
    status: ScoreStatus?,
    autoStart: Boolean?
){
    isVisible = status != null

    if(autoStart == true && !isVisible) return

    val rawId = when (status) {
        ScoreStatus.APPROVE -> R.raw.approved
        ScoreStatus.REJECTED -> R.raw.disapproved
        ScoreStatus.ERROR -> R.raw.disapproved
        else -> null
    }

    if(rawId != null) setAnimation(rawId)
    if(autoStart != null && autoStart) playAnimation()
}