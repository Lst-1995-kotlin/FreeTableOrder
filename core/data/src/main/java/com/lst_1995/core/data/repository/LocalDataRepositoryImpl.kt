package com.lst_1995.core.data.repository

import android.util.Log
import com.lst_1995.core.data.datasource.LocalDataStore
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
        override suspend fun savePlayMode(mode: String) {
            try {
                CoroutineScope(Dispatchers.IO)
                    .async {
                        Log.d("LocalDataRepositoryImpl", "모드 $mode")
                        localDataStore.savePlayMode(mode)
                    }.await()
            } catch (e: Exception) {
                Log.e("LocalDataRepositoryImpl", "LocalDataStore Save PlayMode Failed", e)
            }
        }

        override fun getPlayModeFlow() = localDataStore.getPlayModeFlow()
    }
