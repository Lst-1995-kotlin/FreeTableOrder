package com.lst_1995.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocalDataRepository {
    suspend fun savePlayMode(mode: String)

    fun getPlayModeFlow(): Flow<String>

    suspend fun saveThemeMode(theme: Int)

    fun getThemeMode(): Flow<Int>
}
