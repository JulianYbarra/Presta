package com.junka.presta.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junka.domain.Loan
import com.junka.presta.R
import com.junka.presta.common.basicDiffUtil
import com.junka.presta.common.inflate
import com.junka.presta.databinding.ViewLoansLayoutBinding

class LoanAdapter(
    private val onClick: (Loan) -> Unit,
    private val onLongClick: (Loan) -> Unit
) : ListAdapter<Loan, LoanAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_loans_layout,false)
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
        private val binding = ViewLoansLayoutBinding.bind(view)

        fun bind(loan: Loan){
            binding.user = loan
        }
    }
}