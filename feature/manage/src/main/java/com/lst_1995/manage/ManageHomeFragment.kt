package com.lst_1995.manage

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
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
        setMoveFragment()
    }

    private fun setMoveFragment() {
        val navOption =
            NavOptions
                .Builder()
                .setPopUpTo(R.id.manageHomeFragment, true)
                .build()
        binding.run {
            menuManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_manuManageFragment,navOption)
            }
            salesHistoryCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_salesHistoryFragment,navOption)
            }
            tableManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_manageTableFragment,navOption)
            }
            themeManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_manageThemeFragment,navOption)
            }
            passwordManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_managePasswordFragment,navOption)
            }
            waitScreenManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_manageWaitScreenFragment,navOption)
            }
        }
    }

    private fun navigateTo(destinationId: Int, navOptions: NavOptions? = null) {
        findNavController().navigate(destinationId, null, navOptions)
    }
}
