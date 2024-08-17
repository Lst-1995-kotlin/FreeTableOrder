package com.lst_1995.login

import androidx.lifecycle.ViewModel
import com.lst_1995.core.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val authUseCase: AuthUseCase,
    ) : ViewModel() {
        fun firebaseAuthWithGoogle(idToken: String) {
            authUseCase.firebaseAuthWithGoogle(idToken)
        }
    }
