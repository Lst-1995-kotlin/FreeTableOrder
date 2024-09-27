package com.lst_1995.login

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.domain.usecase.Theme
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.login.databinding.FragmentSelectModeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelectModeFragment : BaseFragment<FragmentSelectModeBinding>(R.layout.fragment_select_mode) {
    private val viewModel: SelectModeViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        setBinding()
        setupToolbarNavigation()
        setUpBackPress()
        setObserver()
        loadTheme()
    }

    private fun setBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun loadTheme() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.themeState.collect { theme ->
                        when (theme) {
                            Theme.LIGHT -> {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            }

                            Theme.DARK -> {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                            }

                            Theme.SYSTEM -> {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUpBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.signOut()
        }
    }

    private fun setupToolbarNavigation() {
        binding.materialToolbar.setNavigationOnClickListener {
            viewModel.signOut()
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.loginState.collect { state ->
                        if (!state) {
                            val navOption =
                                NavOptions
                                    .Builder()
                                    .setPopUpTo(R.id.selectModeFragment, true)
                                    .build()
                            findNavController().navigate(
                                R.id.action_selectModeFragment_to_loginFragment,
                                null,
                                navOption,
                            )
                        }
                    }
                }
                launch {
                    viewModel.selectMode.collect { mode ->
                        if (mode != ModeType.NONE.name) {
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
}
