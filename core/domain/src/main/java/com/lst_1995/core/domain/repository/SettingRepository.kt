package com.lst_1995.core.domain.repository

import com.lst_1995.core.domain.model.ResultType
import com.lst_1995.core.domain.usecase.Theme
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    suspend fun setTablePassword(password: String): ResultType

    suspend fun saveAppTheme(mode: Int): ResultType

    fun loadAppThemeFlow(): Flow<Theme>
}
