package com.lst_1995.manage

import android.os.Bundle
import android.view.View
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.manage.databinding.FragmentManagePasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagePasswordFragment : BaseFragment<FragmentManagePasswordBinding>(R.layout.fragment_manage_password) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
    }
}
