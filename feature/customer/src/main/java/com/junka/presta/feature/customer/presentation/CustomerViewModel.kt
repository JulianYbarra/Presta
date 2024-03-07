package com.junka.presta.feature.customer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.presta.core.model.Customer
import com.junka.presta.core.common.Resource
import com.junka.presta.feature.customer.domain.DeleteCustomerUseCase
import com.junka.presta.feature.customer.domain.GetCustomersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val getCustomersUseCase: GetCustomersUseCase,
    private val deleteCustomerUseCase: DeleteCustomerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.update { UiState(loading = true) }

            val result = getCustomersUseCase()
            _state.update {
                when (result) {
                    is Resource.Failure -> UiState(error = result.error, customers = emptyList())
                    is Resource.Success -> UiState(customers = result.data)
                    else -> it
                }
            }

        }
    }

    fun onDelete(customer: Customer) {
        viewModelScope.launch {
            val result = deleteCustomerUseCase(customer)
            when (result) {
                is Resource.Success -> refresh()
                else -> Unit
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val customers: List<Customer>? = null,
        val error: com.junka.presta.core.common.Error? = null
    )
}