package com.lst_1995.main

import androidx.lifecycle.ViewModel
import com.lst_1995.core.domain.usecase.ModeUseCase
import com.lst_1995.core.domain.usecase.ThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val modeUseCase: ModeUseCase,
        private val themeUseCase: ThemeUseCase,
    ) : ViewModel() {
        val mode = modeUseCase.getPlayModeFlow()
        val theme = themeUseCase.loadAppThemeFlow()
    }
