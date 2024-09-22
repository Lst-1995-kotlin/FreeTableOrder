package com.lst_1995.core.domain.repository

import com.lst_1995.core.domain.model.ResultType
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    suspend fun setTablePassword(password: String): ResultType

    fun saveAppTheme(theme: Int)

    suspend fun loadAppThemeFlow(): Flow<Int>
}
