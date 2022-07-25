package com.junka.presta.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.junka.presta.R
import com.junka.presta.common.launchAndCollect
import com.junka.presta.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val userAdapter by lazy {
        LoanAdapter(
            onClick = {
                val action = HomeFragmentDirections.actionHomeDestToLoansUpdateDest(it.id)
                findNavController().navigate(action)
            },
            onLongClick = { user ->
                viewModel.onDelete(user)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        binding.setUi()

        launchAndCollect(viewModel.state, Lifecycle.State.RESUMED) { setUiState(it) }
    }

    private fun FragmentHomeBinding.setUi() {
        recycler.adapter = userAdapter
        createFab.setOnClickListener {
            findNavController().navigate(R.id.loansCreateDest)
        }
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun setUiState(uiState: HomeViewModel.UiState) {
        binding.apply {
            loading = uiState.loading
            users = uiState.loans
        }
    }
}