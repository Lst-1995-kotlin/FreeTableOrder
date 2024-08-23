package com.lst_1995.core.data.repository

import android.util.Log
import com.lst_1995.core.data.datasource.LocalDataStore
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.domain.model.ResultType
import com.lst_1995.core.domain.repository.LocalDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class LocalDataRepositoryImpl
    @Inject
    constructor(
        private val localDataStore: LocalDataStore,
    ) : LocalDataRepository {
        override suspend fun savePlayMode(mode: String): ResultType =
            try {
                CoroutineScope(Dispatchers.IO)
                    .async {
                        Log.d("LocalDataRepositoryImpl", "모드 $mode")
                        localDataStore.savePlayMode(mode)
                        ResultType.SUCCESS
                    }.await()
            } catch (e: Exception) {
                Log.e("LocalDataRepositoryImpl", "LocalDataStore Save PlayMode Failed", e)
                ResultType.FAILURE
            }

        override suspend fun getPlayMode(): String =
            try {
                CoroutineScope(Dispatchers.IO)
                    .async {
                        return@async localDataStore.getPlayMode()
                    }.await()
            } catch (e: Exception) {
                Log.e("LocalDataRepositoryImpl", "LocalDataStore Get PlayMode Failed", e)
                ""
            }
    }
