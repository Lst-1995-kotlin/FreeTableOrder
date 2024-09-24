package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.model.ResultType
import com.lst_1995.core.domain.repository.SettingRepository
import com.lst_1995.core.domain.util.NetworkManager
import javax.inject.Inject

enum class Theme {
    LIGHT,
    DARK,
    SYSTEM
}

class ThemeUseCase
    @Inject
    constructor(
        private val settingRepository: SettingRepository,
        private val networkManager: NetworkManager,
    ) {
        suspend fun saveAppTheme(mode: Int): ResultType {
            if (networkManager.isNetworkAvailable()) {
                return settingRepository.saveAppTheme(mode)
            }
            return ResultType.FAILURE
        }

        fun loadAppThemeFlow() = settingRepository.loadAppThemeFlow()
    }
