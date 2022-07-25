package com.junka.presta.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junka.domain.Loan

@BindingAdapter("items")
fun RecyclerView.setItems(loans: List<Loan>?) {
    if (loans != null) {
        (adapter as? LoanAdapter)?.submitList(loans)
    }
}