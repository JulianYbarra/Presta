package com.junka.presta.ui.loans.update

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.domain.Error
import com.junka.domain.Resource
import com.junka.domain.Loan
import com.junka.usecases.GetLoanByIdUseCase
import com.junka.usecases.ScoreLoanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoansUpdateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getLoanByIdUseCase: GetLoanByIdUseCase,
    private val scoreLoanUseCase: ScoreLoanUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    val id = LoansUpdateFragmentArgs.fromSavedStateHandle(savedStateHandle).id

    init {
        viewModelScope.launch {
            _state.update { UiState(loading = true) }
            val result = getLoanByIdUseCase(id)
            _state.update {
                when (result) {
                    is Resource.Error -> UiState(error = result.error)
                    is Resource.Success -> UiState(loan = result.data)
                }
            }
        }
    }

    fun update(name : String,lastName : String,dni : Int){
        viewModelScope.launch {
            _state.update { _state.value.copy(loading = true) }

            val user = _state.value.loan?.copy(name= name , lastName = lastName, dni = dni)
            user?.let {
                val result = scoreLoanUseCase(user)

                _state.update {
                    when (result) {
                        is Resource.Error -> UiState(error = result.error)
                        is Resource.Success -> UiState(
                            loading = true,
                            loan = result.data,
                            status = result.data.status
                        )
                    }
                }
            } ?: run {
                _state.update { UiState(error = Error.Unknown("Loan is null")) }
            }
        }
    }


    data class UiState(
        val loading: Boolean = false,
        val loan: Loan? = null,
        val status : Loan.Status? = null,
        val error: Error? = null
    )
}