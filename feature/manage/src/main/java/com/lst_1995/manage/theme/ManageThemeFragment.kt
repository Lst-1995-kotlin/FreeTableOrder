package com.lst_1995.manage.theme

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.lst_1995.core.domain.usecase.Theme
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.manage.R
import com.lst_1995.manage.databinding.FragmentManageThemeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ManageThemeFragment : BaseFragment<FragmentManageThemeBinding>(R.layout.fragment_manage_theme) {
    private val viewModel: ManageThemeViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setObserver()
        setRadioButton()
        setBackStack()
    }

    private fun setBackStack() {
        setBackStackByToolbar(binding.materialToolbar3)
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { observeTheme() }
            }
        }
    }

    private suspend fun observeTheme() {
        viewModel.appTheme.collect { theme ->
            when (theme) {
                Theme.LIGHT -> binding.lightThemeButton.isChecked = true
                Theme.DARK -> binding.darkThemeButton.isChecked = true
                Theme.SYSTEM -> binding.systemDefaultThemeButton.isChecked = true
            }
        }
    }

    private fun setRadioButton() {
        binding.themeRg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.systemDefaultThemeButton -> {
                    viewModel.changeSystemTheme()
                }

                R.id.lightThemeButton -> {
                    viewModel.changeLightTheme()
                }

                R.id.darkThemeButton -> {
                    viewModel.changeDarkTheme()
                }
            }
        }
    }
}
