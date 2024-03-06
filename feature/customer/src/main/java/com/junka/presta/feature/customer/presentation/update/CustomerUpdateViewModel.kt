package com.junka.presta.feature.customer.presentation.update

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.presta.core.model.Customer
import com.junka.presta.core.model.ScoreStatus
import com.junka.presta.core.common.Error
import com.junka.presta.core.common.Resource
import com.junka.presta.feature.customer.domain.GetCustomerByIdUseCase
import com.junka.presta.feature.customer.domain.ScoreCustomerUseCase
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

    val id : String = savedStateHandle["id"] ?: ""

    init {
        viewModelScope.launch {
            _state.update { UiState(loading = true) }
            val result = getCustomerByIdUseCase(id)
            _state.update {
                when (result) {
                    is Resource.Failure -> UiState(error = result.error)
                    is Resource.Success -> UiState(customer = result.data)
                    else -> it
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
                        is Resource.Failure -> UiState(error = result.error)
                        is Resource.Success -> UiState(
                            loading = true,
                            customer = result. data,
                            status = result.data.score?.getScoreStatus()
                        )
                        else -> it
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
        val status : ScoreStatus? = null,
        val error: Error? = null
    )
}