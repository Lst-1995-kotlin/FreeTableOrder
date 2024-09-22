package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.repository.SettingRepository
import javax.inject.Inject

class ThemeUseCase
    @Inject
    constructor(
        private val settingRepository: SettingRepository,
    ) {
        fun saveAppTheme(theme: Int) {
            settingRepository.saveAppTheme(theme)
        }

        suspend fun loadAppThemeFlow() {
            settingRepository.loadAppThemeFlow()
        }
    }
