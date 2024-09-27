package com.lst_1995.manage.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.manage.R
import com.lst_1995.manage.databinding.FragmentManuManageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuManageFragment : BaseFragment<FragmentManuManageBinding>(R.layout.fragment_manu_manage) {
    private val viewModel: MenuManageViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setBinding()
    }

    private fun setBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
