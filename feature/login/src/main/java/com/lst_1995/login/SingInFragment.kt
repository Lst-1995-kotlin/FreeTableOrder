package com.lst_1995.login

import android.os.Bundle
import android.view.View
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.login.databinding.FragmentSingInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingInFragment : BaseFragment<FragmentSingInBinding>(R.layout.fragment_sing_in) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
    }
}
