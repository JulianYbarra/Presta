package com.junka.presta.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junka.domain.Customer

@BindingAdapter("items")
fun RecyclerView.setItems(customers: List<Customer>?) {
    if (customers != null) {
        (adapter as? CustomerAdapter)?.submitList(customers)
    }
}