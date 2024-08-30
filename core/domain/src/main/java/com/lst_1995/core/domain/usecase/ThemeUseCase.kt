package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class ThemeUseCase
    @Inject
    constructor(
        private val localDataRepository: LocalDataRepository,
    ) {
        suspend fun saveThemeMode(theme: Int) = localDataRepository.saveThemeMode(theme)

        fun getThemeModeFlow() = localDataRepository.getThemeModeFlow()
    }
