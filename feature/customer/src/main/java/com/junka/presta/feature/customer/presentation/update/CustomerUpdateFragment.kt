package com.junka.presta.feature.customer.presentation.update

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.junka.presta.core.ui.extensions.launchAndCollect
import com.junka.presta.feature.customer.databinding.FragmentCustomerUpdateBinding
import com.junka.presta.feature.customer.presentation.update.CustomerUpdateViewModel.UiState
import dagger.hilt.android.AndroidEntryPoint
import com.junka.presta.feature.customer.R
import com.junka.presta.feature.customer.presentation.setAnimation
import com.junka.presta.core.designsystem.R as DSR

@AndroidEntryPoint
class CustomerUpdateFragment : BottomSheetDialogFragment() {

    private var _binding : FragmentCustomerUpdateBinding? = null
    private val binding : FragmentCustomerUpdateBinding
        get() = _binding!!

    private val viewModel by viewModels<CustomerUpdateViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_customer_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCustomerUpdateBinding.bind(view)
        setUi(binding)

        launchAndCollect(viewModel.state) { setUiState(it) }
    }

    private fun setUi(binding: FragmentCustomerUpdateBinding) = with(binding) {
        saveBtn.setOnClickListener {
            if (validateForm()) {
                viewModel.update(
                    name = nameEt.text.toString(),
                    lastName = lastnameEt.text.toString(),
                    dni = dniEt.text.toString().toInt()
                )
            }
        }
        cancelBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        stateAnimation.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator) {
                loadingAnimation.isVisible = false
            }

            override fun onAnimationEnd(p0: Animator) {
                findNavController().popBackStack()
            }

            override fun onAnimationCancel(p0: Animator) = Unit

            override fun onAnimationRepeat(p0: Animator) = Unit
        })
    }

    private fun setUiState(uiState: UiState) = with(binding) {
        loadingContainer.isVisible = uiState.loading
        saveBtn.isEnabled = !uiState.loading
        uiState.customer?.let { customer ->
            nameEt.setText(customer.name)
            lastnameEt.setText(customer.lastName)
            dniEt.setText(customer.dni.toString())
        }

        uiState.status?.let {
            stateAnimation.setAnimation(it,true)
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        with(binding) {

            nameL.error = validateEmptyString(nameEt.text.toString()).also {
                if(it != null)
                    isValid = false
            }

            lastnameL.error = validateEmptyString(lastnameEt.text.toString()).also {
                if(it != null)
                    isValid = false
            }

            dniL.error = validateEmptyString(dniEt.text.toString()).also {
                if(it != null)
                    isValid = false
            }

            dniL.error = validateStringIsNumber(dniEt.text.toString()).also {
                if(it != null)
                    isValid = false
            }
        }

        return isValid
    }

    private fun validateEmptyString(str: String): String? {
        return if (str.isEmpty()) {
            getString(DSR.string.form_required_field)
        } else {
            null
        }
    }

    private fun validateStringIsNumber(str: String): String? {
        return if (!str.any { it.isDigit() }) {
            getString(DSR.string.form_required_number_field)
        }else{
            null
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}