package com.lst_1995.manage.theme

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
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
        observeTheme()
        setRadioButton()
    }

    private fun observeTheme() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.appTheme.collect { theme ->
                        when (theme) {
                            Theme.LIGHT -> {
                                binding.lightThemeButton.isChecked = true
                                changeTheme(AppCompatDelegate.MODE_NIGHT_NO)
                            }
                            Theme.DARK -> {
                                binding.darkThemeButton.isChecked = true
                                changeTheme(AppCompatDelegate.MODE_NIGHT_YES)
                            }
                            Theme.SYSTEM -> {
                                binding.systemDefaultThemeButton.isChecked = true
                                changeTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setRadioButton() {
        binding.themeRg.setOnCheckedChangeListener { _, checkedId ->
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

    private fun changeTheme(theme: Int) {
        AppCompatDelegate.setDefaultNightMode(theme)
    }
}
