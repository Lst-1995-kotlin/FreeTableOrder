package com.lst_1995.manage.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.usecase.ThemeUseCase
import com.lst_1995.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageThemeViewModel
    @Inject
    constructor(
        private val themeUseCase: ThemeUseCase,
    ) : BaseViewModel() {
        val appTheme = themeUseCase.loadAppThemeFlow()

        fun changeLightTheme() {
            viewModelScope.launch {
                themeUseCase.saveAppTheme(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        fun changeDarkTheme() {
            viewModelScope.launch {
                themeUseCase.saveAppTheme(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        fun changeSystemTheme() {
            viewModelScope.launch {
                themeUseCase.saveAppTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
