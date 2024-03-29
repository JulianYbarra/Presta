package com.junka.presta.feature.customer.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junka.presta.core.model.Customer
import com.junka.presta.core.ui.extensions.basicDiffUtil
import com.junka.presta.core.ui.extensions.inflate
import com.junka.presta.core.ui.extensions.setColor
import com.junka.presta.core.ui.extensions.setTextColor
import com.junka.presta.feature.customer.R
import com.junka.presta.feature.customer.databinding.ViewCustomerLayoutBinding

class CustomerAdapter(
    private val onClick: (Customer) -> Unit,
    private val onLongClick: (Customer) -> Unit
) : ListAdapter<Customer, CustomerAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_customer_layout,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loan = getItem(position)
        holder.bind(loan)
        holder.itemView.setOnClickListener {
            onClick(loan)
        }
        holder.itemView.setOnLongClickListener {
            onLongClick(loan)
            true
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewCustomerLayoutBinding.bind(view)

        fun bind(customer: Customer) = with(binding){
            nameTv.text = customer.fullName
            stateView.setColor(customer.score?.getScoreStatus())
            dniTv.text = customer.dni.toString()
            statusTv.apply {
                text = customer.score?.status.orEmpty()
                setTextColor(customer.score?.getScoreStatus())
            }
        }
    }
}