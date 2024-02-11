package com.junka.presta.ui.customer.update

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.domain.Error
import com.junka.domain.Resource
import com.junka.domain.Customer
import com.junka.usecases.GetCustomerByIdUseCase
import com.junka.usecases.ScoreCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerUpdateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCustomerByIdUseCase: GetCustomerByIdUseCase,
    private val scoreCustomerUseCase: ScoreCustomerUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    val id = CustomerUpdateFragmentArgs.fromSavedStateHandle(savedStateHandle).id

    init {
        viewModelScope.launch {
            _state.update { UiState(loading = true) }
            val result = getCustomerByIdUseCase(id)
            _state.update {
                when (result) {
                    is Resource.Error -> UiState(error = result.error)
                    is Resource.Success -> UiState(customer = result.data)
                }
            }
        }
    }

    fun update(name : String,lastName : String,dni : Int){
        viewModelScope.launch {
            _state.update { _state.value.copy(loading = true) }

            val customer = _state.value.customer?.copy(name= name , lastName = lastName, dni = dni)
            customer?.let {
                val result = scoreCustomerUseCase(customer)

                _state.update {
                    when (result) {
                        is Resource.Error -> UiState(error = result.error)
                        is Resource.Success -> UiState(
                            loading = true,
                            customer = result.data,
                            status = result.data.status
                        )
                    }
                }
            } ?: run {
                _state.update { UiState(error = Error.Unknown("customer is null")) }
            }
        }
    }


    data class UiState(
        val loading: Boolean = false,
        val customer: Customer? = null,
        val status : Customer.Status? = null,
        val error: Error? = null
    )
}