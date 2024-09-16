package com.lst_1995.manage.password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }
}
