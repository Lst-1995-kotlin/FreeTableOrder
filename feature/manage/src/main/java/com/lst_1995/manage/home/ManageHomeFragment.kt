package com.lst_1995.manage.home

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.manage.R
import com.lst_1995.manage.databinding.FragmentManageHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ManageHomeFragment : BaseFragment<FragmentManageHomeBinding>(R.layout.fragment_manage_home) {
    private val viewModel: ManageHomeViewModel by viewModels()
    private val navOption =
        NavOptions
            .Builder()
            .setPopUpTo(R.id.manageHomeFragment, false)
            .build()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setUpSelectMode()
        setChangeMode()
        setObserver()
    }

    private fun setChangeMode() {
        binding.materialToolbar.setNavigationOnClickListener {
            viewModel.changeMode()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.changeMode()
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.loginState.collect { state ->
                        if (state == ModeType.NONE.name) {
                            val intent = Intent()
                            intent.component =
                                ComponentName(
                                    "com.lst_1995.freetableorder",
                                    "com.lst_1995.main.FeatureMainActivity",
                                )
                            startActivity(intent)
                            activity?.finish()
                        }
                    }
                }
            }
        }
    }

    private fun setUpSelectMode() {
        binding.run {
            menuManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_manuManageFragment, navOption)
            }
            salesHistoryCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_salesHistoryFragment, navOption)
            }
            tableManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_manageTableFragment, navOption)
            }
            themeManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_manageThemeFragment, navOption)
            }
            passwordManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_managePasswordFragment, navOption)
            }
            waitScreenManageCv.setOnClickListener {
                navigateTo(R.id.action_manageHomeFragment_to_manageWaitScreenFragment, navOption)
            }
        }
    }

    private fun navigateTo(
        destinationId: Int,
        navOptions: NavOptions? = null,
    ) {
        findNavController().navigate(destinationId, null, navOptions)
    }
}
