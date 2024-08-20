package com.lst_1995.core.domain.repository

import com.lst_1995.core.domain.model.ResultType

interface LocalDataRepository {
    suspend fun savePlayMode(mode: String): ResultType

    suspend fun getPlayMode(): String
}
