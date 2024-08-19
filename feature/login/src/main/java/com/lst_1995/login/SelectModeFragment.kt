package com.lst_1995.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.login.databinding.FragmentSelectModeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectModeFragment : BaseFragment<FragmentSelectModeBinding>(R.layout.fragment_select_mode) {
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
