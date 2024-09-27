package com.lst_1995.manage.waitscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.manage.R
import com.lst_1995.manage.databinding.FragmentManageWaitScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageWaitScreenFragment : BaseFragment<FragmentManageWaitScreenBinding>(R.layout.fragment_manage_wait_screen) {
    private val viewModel: ManageWaitScreenViewModel by viewModels()

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
