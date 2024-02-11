package com.junka.presta.common

import android.content.Context
import com.junka.domain.Customer
import com.junka.domain.Customer.Status.*
import com.junka.presta.R

fun Context.statusColor(status: Customer.Status) = when (status) {
    ERROR -> getColor(R.color.md_theme_tertiary)
    APPROVE -> getColor(R.color.md_theme_primary)
    REJECTED -> getColor(R.color.md_theme_error)
}