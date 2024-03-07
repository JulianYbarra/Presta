package com.junka.presta.feature.customer.presentation

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.junka.presta.core.ui.extensions.errorToString
import com.junka.presta.core.ui.extensions.launchAndCollect
import com.junka.presta.feature.customer.R
import com.junka.presta.feature.customer.databinding.FragmentCustomerBinding
import com.junka.presta.feature.customer.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerFragment : Fragment(R.layout.fragment_customer) {

    private var _binding : FragmentCustomerBinding? = null
    private val binding: FragmentCustomerBinding
        get() = _binding!!

    private val viewModel by viewModels<CustomerViewModel>()
    private val customerAdapter by lazy {
        CustomerAdapter(
            onClick = {
                val request = NavDeepLinkRequest.Builder
                    .fromUri(Navigation.Update(it.id).getArgRoute().toUri())
                    .build()
                findNavController().navigate(request)
            },
            onLongClick = { customer ->
                viewModel.onDelete(customer)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCustomerBinding.bind(view)
        setUi(binding)

        launchAndCollect(viewModel.state, Lifecycle.State.RESUMED) { setUiState(it) }
    }

    private fun setUi(binding : FragmentCustomerBinding) = with(binding) {
        recycler.adapter = customerAdapter
        createFab.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri(Navigation.Create.route.toUri())
                .build()
            findNavController().navigate(request)
        }
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun setUiState(uiState: CustomerViewModel.UiState) = with(binding) {
        progress.isVisible = uiState.loading
        customerAdapter.submitList(uiState.customers)
        errorTv.text = uiState.error?.errorToString(requireContext()).orEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}