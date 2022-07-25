package com.junka.presta.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.domain.Error
import com.junka.domain.Resource
import com.junka.domain.Loan
import com.junka.usecases.DeleteLoanUseCase
import com.junka.usecases.GetLoansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLoansUseCase: GetLoansUseCase,
    private val deleteLoanUseCase: DeleteLoanUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.update { UiState(loading = true) }

            val result = getLoansUseCase()
            _state.update {
                when (result) {
                    is Resource.Error -> UiState(error = result.error, loans = emptyList())
                    is Resource.Success -> UiState(loans = result.data)
                }
            }

        }
    }

    fun onDelete(loan: Loan) {
        viewModelScope.launch {
            val result = deleteLoanUseCase(loan)
            when (result) {
                is Resource.Error -> Unit
                is Resource.Success -> refresh()
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val loans: List<Loan>? = null,
        val error: Error? = null
    )
}