package com.junka.presta.ui.customer.create

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.junka.presta.R
import com.junka.presta.common.launchAndCollect
import com.junka.presta.databinding.FragmentCustomerCreateBinding
import com.junka.presta.ui.customer.create.CustomerCreateViewModel.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerCreateFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCustomerCreateBinding
    private val viewModel by viewModels<CustomerCreateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_customer_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCustomerCreateBinding.bind(view)
        binding.setUi()

        launchAndCollect(viewModel.state) { setUiState(it) }
    }

    private fun FragmentCustomerCreateBinding.setUi() {
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
        binding.loading = uiState.loading
        uiState.status?.let {
            binding.status = it.getScoreStatus()
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
            getString(R.string.form_required_field)
        } else {
            null
        }
    }

    private fun validateStringIsNumber(str: String): String? {
        return if (str.toIntOrNull() == null) {
            getString(R.string.form_required_number_field)
        }else{
            null
        }

    }


}