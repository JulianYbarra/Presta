package com.junka.presta.feature.customer.presentation.create

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
import com.junka.presta.feature.customer.R as CustomerR
import com.junka.presta.core.designsystem.R as DesignSystemR
import com.junka.presta.feature.customer.databinding.FragmentCustomerCreateBinding
import com.junka.presta.feature.customer.presentation.create.CustomerCreateViewModel.UiState
import com.junka.presta.feature.customer.presentation.setAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerCreateFragment : BottomSheetDialogFragment() {

    private var _binding : FragmentCustomerCreateBinding? = null
    private val binding : FragmentCustomerCreateBinding
        get() = _binding!!

    private val viewModel by viewModels<CustomerCreateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(CustomerR.layout.fragment_customer_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCustomerCreateBinding.bind(view)
        setUi(binding)

        launchAndCollect(viewModel.state) { setUiState(it) }
    }

    private fun setUi(binding: FragmentCustomerCreateBinding) = with(binding) {
        saveBtn.setOnClickListener {
            if (validateForm()) {
                viewModel.create(
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
        saveBtn.isEnabled = !uiState.loading
        loadingContainer.isVisible = uiState.loading
        uiState.status?.let {
            stateAnimation.setAnimation(it.getScoreStatus(),true)
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
            getString(DesignSystemR.string.form_required_field)
        } else {
            null
        }
    }

    private fun validateStringIsNumber(str: String): String? {
        return if (str.toIntOrNull() == null) {
            getString(DesignSystemR.string.form_required_number_field)
        }else{
            null
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}