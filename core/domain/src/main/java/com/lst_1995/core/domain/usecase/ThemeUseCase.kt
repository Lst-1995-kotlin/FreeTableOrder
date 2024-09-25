package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.model.ResultType
import com.lst_1995.core.domain.repository.SettingRepository
import javax.inject.Inject

enum class Theme {
    LIGHT,
    DARK,
    SYSTEM,
}

class ThemeUseCase
    @Inject
    constructor(
        private val settingRepository: SettingRepository,
    ) {
        suspend fun saveAppTheme(mode: Int): ResultType = settingRepository.saveAppTheme(mode)

        fun loadAppThemeFlow() = settingRepository.loadAppThemeFlow()
    }
