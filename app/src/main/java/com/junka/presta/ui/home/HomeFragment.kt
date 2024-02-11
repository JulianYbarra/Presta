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
    private val customerAdapter by lazy {
        CustomerAdapter(
            onClick = {
                val action = HomeFragmentDirections.toCustomerUpdateDest(it.id)
                findNavController().navigate(action)
            },
            onLongClick = { customer ->
                viewModel.onDelete(customer)
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
        recycler.adapter = customerAdapter
        createFab.setOnClickListener {
            findNavController().navigate(R.id.customerCreateDest)
        }
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun setUiState(uiState: HomeViewModel.UiState) {
        binding.apply {
            loading = uiState.loading
            customers = uiState.customers
        }
    }
}