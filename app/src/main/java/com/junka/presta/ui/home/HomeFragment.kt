package com.junka.presta.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.junka.presta.R
import com.junka.presta.common.errorToString
import com.junka.presta.common.launchAndCollect
import com.junka.presta.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

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

        _binding = FragmentHomeBinding.bind(view)
        setUi(binding)

        launchAndCollect(viewModel.state, Lifecycle.State.RESUMED) { setUiState(it) }
    }

    private fun setUi(binding : FragmentHomeBinding) = with(binding) {
        recycler.adapter = customerAdapter
        createFab.setOnClickListener {
            findNavController().navigate(R.id.customerCreateDest)
        }
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun setUiState(uiState: HomeViewModel.UiState) = with(binding) {
        progress.isVisible = uiState.loading
        customerAdapter.submitList(uiState.customers)
        errorTv.text = uiState.error?.errorToString(requireContext()).orEmpty()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}