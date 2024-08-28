package com.lst_1995.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val authUseCase: AuthUseCase,
    ) : ViewModel() {
        val loginState: Flow<Boolean> = authUseCase.loginStateFlow()

        fun firebaseAuthWithGoogle(idToken: String) {
            viewModelScope.launch {
                authUseCase.firebaseAuthWithGoogle(idToken)
            }
        }
    }
