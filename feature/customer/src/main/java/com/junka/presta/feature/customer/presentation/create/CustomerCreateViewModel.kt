package com.junka.presta.feature.customer.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.presta.core.common.Error
import com.junka.presta.core.model.Customer
import com.junka.presta.core.model.Score
import com.junka.presta.core.common.Resource
import com.junka.presta.feature.customer.domain.ScoreCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerCreateViewModel @Inject constructor(
    private val scoreCustomerUseCase: ScoreCustomerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun create(name : String,lastName : String,dni : Int){
        viewModelScope.launch {
            _state.update { _state.value.copy(loading = true) }

            val customer = Customer(
                id = "",
                name = name,
                lastName = lastName,
                dni = dni,
                score = null
            )
            val result = scoreCustomerUseCase(customer)

            _state.update {
                when(result){
                    is Resource.Failure -> UiState(error = result.error)
                    is Resource.Success -> UiState(loading = true,status = result.data.score)
                    else -> it
                }
            }
        }
    }

    data class UiState(
        val loading : Boolean = false,
        val status : Score? = null,
        val error : Error? = null
    )
}