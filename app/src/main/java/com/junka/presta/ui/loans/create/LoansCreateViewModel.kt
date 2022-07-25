package com.junka.presta.ui.loans.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.domain.Error
import com.junka.domain.Resource
import com.junka.domain.Loan
import com.junka.usecases.ScoreLoanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoansCreateViewModel @Inject constructor(
    private val scoreLoanUseCase: ScoreLoanUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun create(name : String,lastName : String,dni : Int){
        viewModelScope.launch {
            _state.update { _state.value.copy(loading = true) }

            val loan = Loan(
                id = "",
                name = name,
                lastName = lastName,
                dni = dni,
                status = Loan.Status.ERROR
            )
            val result = scoreLoanUseCase(loan)

            _state.update {
                when(result){
                    is Resource.Error -> UiState(error = result.error)
                    is Resource.Success -> UiState(loading = true,status = result.data.status)
                }
            }
        }
    }

    data class UiState(
        val loading : Boolean = false,
        val status : Loan.Status? = null,
        val error : Error? = null
    )
}