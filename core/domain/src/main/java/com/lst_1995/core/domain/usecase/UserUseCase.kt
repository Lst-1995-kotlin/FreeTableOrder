package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.repository.AuthRepository
import com.lst_1995.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class UserUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
        private val localDataRepository: LocalDataRepository,
    ) {
        fun autoLoginCheck() = authRepository.autoLoginCheck()

        fun firebaseSignOut() = authRepository.firebaseSignOut()

        fun firebaseAuthWithGoogle(idToken: String) = authRepository.firebaseAuthWithGoogle(idToken)

        suspend fun savePlayMode(mode: String) = localDataRepository.savePlayMode(mode)

        suspend fun getPlayMode() = localDataRepository.getPlayMode()
    }
