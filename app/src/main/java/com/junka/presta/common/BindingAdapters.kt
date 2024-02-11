package com.junka.presta.common

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.junka.domain.Customer
import com.junka.presta.R

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?) {
    isVisible = visible ?: true
}

@BindingAdapter("statusColor")
fun View.setColor(status: Customer.Status) {
    setBackgroundColor(context.statusColor(status))
}

@BindingAdapter("statusColor")
fun TextView.setTextColor(status: Customer.Status) {
    setTextColor(context.statusColor(status))
}

@BindingAdapter(
    value = [
        "custom_lottie_rawId",
        "custom_lottie_autoPlay"
    ],
    requireAll = false
)
fun LottieAnimationView.setAnimation(
    status: Customer.Status?,
    autoStart: Boolean?
){
    if(autoStart == true && visibility == View.GONE) return

    val rawId = when (status) {
        Customer.Status.APPROVE -> R.raw.approved
        Customer.Status.REJECTED -> R.raw.disapproved
        Customer.Status.ERROR -> R.raw.disapproved
        else -> null
    }

    if(rawId != null) setAnimation(rawId)
    if(autoStart != null && autoStart) playAnimation()
}