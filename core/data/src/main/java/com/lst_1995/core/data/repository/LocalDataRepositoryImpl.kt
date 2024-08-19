package com.lst_1995.core.data.repository

import com.lst_1995.core.data.datasource.LocalDataStore
import com.lst_1995.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class LocalDataRepositoryImpl
    @Inject
    constructor(
        private val localDataStore: LocalDataStore,
    ) : LocalDataRepository {
        override fun savePlayMode() {
            TODO("Not yet implemented")
        }

        override fun getPlayMode() {
            TODO("Not yet implemented")
        }
    }
