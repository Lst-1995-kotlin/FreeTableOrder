package com.lst_1995.manage.theme

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.manage.R
import com.lst_1995.manage.databinding.FragmentManageThemeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageThemeFragment : BaseFragment<FragmentManageThemeBinding>(R.layout.fragment_manage_theme) {
    private val viewModel: ManageThemeViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}
