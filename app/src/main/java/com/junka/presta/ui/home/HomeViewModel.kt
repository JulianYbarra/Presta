package com.junka.presta.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.domain.Error
import com.junka.domain.Resource
import com.junka.domain.Customer
import com.junka.usecases.DeleteCustomerUseCase
import com.junka.usecases.GetCustomersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
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
                    is Resource.Error -> UiState(error = result.error, customers = emptyList())
                    is Resource.Success -> UiState(customers = result.data)
                }
            }

        }
    }

    fun onDelete(customer: Customer) {
        viewModelScope.launch {
            val result = deleteCustomerUseCase(customer)
            when (result) {
                is Resource.Error -> Unit
                is Resource.Success -> refresh()
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val customers: List<Customer>? = null,
        val error: Error? = null
    )
}