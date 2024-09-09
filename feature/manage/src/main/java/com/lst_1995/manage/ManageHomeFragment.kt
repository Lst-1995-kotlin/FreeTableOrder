package com.lst_1995.manage

import android.os.Bundle
import android.view.View
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.manage.databinding.FragmentManageHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageHomeFragment : BaseFragment<FragmentManageHomeBinding>(R.layout.fragment_manage_home) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
    }
}
