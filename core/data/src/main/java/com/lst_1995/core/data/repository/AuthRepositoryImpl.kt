package com.lst_1995.core.data.repository

import com.lst_1995.core.data.datasource.LocalDataStore
import com.lst_1995.core.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl@Inject constructor(
    private val localDataStore: LocalDataStore,
) : AuthRepository {
        override fun loginWithGoogle() {
            TODO("Not yet implemented")
        }

        override fun singupWithGoogle() {
            TODO("Not yet implemented")
        }

        override fun loginWithNaver() {
            TODO("Not yet implemented")
        }

        override fun singupWithNaver() {
            TODO("Not yet implemented")
        }

        override fun loginWithKakao() {
            TODO("Not yet implemented")
        }

        override fun singupWithKakao() {
            TODO("Not yet implemented")
        }
    }
