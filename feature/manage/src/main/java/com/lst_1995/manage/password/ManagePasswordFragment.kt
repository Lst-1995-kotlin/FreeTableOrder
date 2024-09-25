package com.lst_1995.manage.password

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lst_1995.core.domain.usecase.PasswordEvent
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
        viewModel.passwordMessage.observe(viewLifecycleOwner) {
            if (it == PasswordEvent.CHANGE_SUCCESS) {
                showToastMessage(resources.getString(R.string.password_changed_success))
                findNavController().popBackStack()
            }
        }
    }

    private fun setBackStack() {
        setBackStackByToolbar(binding.materialToolbar2)
        setBackStackByButton(binding.cancelBtn)
    }
}

@BindingAdapter("passwordMessage")
fun TextView.passwordMessage(event: PasswordEvent) {
    val messageId =
        when (event) {
            PasswordEvent.CHANGE_BLANK -> R.string.password_input_blank
            PasswordEvent.CHANGE_CHECK_BLANK -> R.string.password_input_check_blank
            PasswordEvent.NOT_MATCH -> R.string.password_not_match
            PasswordEvent.LENGTH -> R.string.password_length
            PasswordEvent.NEWLINE -> R.string.password_newline
            PasswordEvent.SPACE -> R.string.password_space
            PasswordEvent.NONE -> R.string.password_check_success
            PasswordEvent.CHANGE_SUCCESS -> R.string.password_changed_success
        }
    this.text = resources.getString(messageId)
}
