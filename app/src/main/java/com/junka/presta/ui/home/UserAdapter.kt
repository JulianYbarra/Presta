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

class UserAdapter(
    private val onClick: (Loan) -> Unit,
    private val onLongClick: (Loan) -> Unit
) : ListAdapter<Loan, UserAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_loans_layout,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onClick(user)
        }
        holder.itemView.setOnLongClickListener {
            onLongClick(user)
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