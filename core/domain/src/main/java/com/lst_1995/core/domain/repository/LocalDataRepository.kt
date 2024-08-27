package com.lst_1995.core.domain.repository

import com.lst_1995.core.domain.model.ResultType
import kotlinx.coroutines.flow.Flow

interface LocalDataRepository {
    suspend fun savePlayMode(mode: String): ResultType

    fun getPlayMode(): Flow<String>
}
