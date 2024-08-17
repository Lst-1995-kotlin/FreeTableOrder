package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.repository.AuthRepository
import javax.inject.Inject

enum class ResultCode {
    SUCCESS,
    FAILURE,
}

class AuthUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        fun autoLoginCheck() = authRepository.autoLoginCheck()

        fun signOut() = authRepository.signOut()

        suspend fun firebaseAuthWithGoogle(idToken: String) = authRepository.firebaseAuthWithGoogle(idToken)
    }
