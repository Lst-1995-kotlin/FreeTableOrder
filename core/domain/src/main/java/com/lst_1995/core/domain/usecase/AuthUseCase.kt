package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        fun loginWithGoogle(token: String) {
            authRepository.loginWithGoogle(token)
        }
    }
