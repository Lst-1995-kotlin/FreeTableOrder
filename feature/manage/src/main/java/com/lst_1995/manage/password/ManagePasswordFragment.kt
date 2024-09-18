package com.lst_1995.manage.password

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lst_1995.core.domain.usecase.PasswordErrorType
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.manage.R
import com.lst_1995.manage.databinding.FragmentManagePasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagePasswordFragment : BaseFragment<FragmentManagePasswordBinding>(R.layout.fragment_manage_password) {
    private val viewModel: ManagePasswordViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setBackStack()
        setObserver()
    }

    private fun setObserver() {
        viewModel.complete.observe(viewLifecycleOwner) {
            if (it) {
                val dialog = MaterialAlertDialogBuilder(requireContext())
                dialog.setMessage(R.string.password_changed_success)
                dialog.setPositiveButton(R.string.check) { _, _ ->
                    findNavController().popBackStack()
                }
                dialog.show()
            }
        }
    }

    private fun setBackStack() {
        setBackStackByToolbar(binding.materialToolbar2)
        setBackStackByButton(binding.cancelBtn)
    }
}

@BindingAdapter("passwordMessage")
fun TextView.passwordMessage(type: PasswordErrorType) {
    val messageId =
        when (type) {
            PasswordErrorType.CHANGE_BLANK -> R.string.password_input_blank
            PasswordErrorType.CHANGE_CHECK_BLANK -> R.string.password_input_check_blank
            PasswordErrorType.NOT_MATCH -> R.string.password_not_match
            PasswordErrorType.LENGTH -> R.string.password_length
            PasswordErrorType.NEWLINE -> R.string.password_newline
            PasswordErrorType.SPACE -> R.string.password_space
            PasswordErrorType.NONE -> R.string.password_check_success
        }
    this.text = resources.getString(messageId)
}
