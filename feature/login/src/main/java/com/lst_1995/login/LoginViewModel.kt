package com.lst_1995.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.usecase.AuthUseCase
import com.lst_1995.core.domain.usecase.ResultCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val authUseCase: AuthUseCase,
    ) : ViewModel() {
        private val _loginState = MutableLiveData<Boolean>()
        val loginState: LiveData<Boolean> get() = _loginState

        fun firebaseAuthWithGoogle(idToken: String) {
            viewModelScope.launch {
                when (authUseCase.firebaseAuthWithGoogle(idToken)) {
                    ResultCode.SUCCESS -> _loginState.value = true
                    ResultCode.FAILURE -> _loginState.value = false
                }
            }
        }

        fun autoLoginCheck() {
            _loginState.value = authUseCase.autoLoginCheck()
        }

        fun signOut() {
            authUseCase.signOut()
            autoLoginCheck()
        }
    }
