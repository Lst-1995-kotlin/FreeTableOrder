package com.lst_1995.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.domain.usecase.AuthUseCase
import com.lst_1995.core.domain.usecase.ModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectModeViewModel
    @Inject
    constructor(
        private val modeUseCase: ModeUseCase,
        private val authUseCase: AuthUseCase,
    ) : ViewModel() {
        private val _loginState = MutableLiveData<Boolean>()
        val loginState: LiveData<Boolean> get() = _loginState

        fun setManageMode() {
            savePlayMode(ModeType.MANAGE)
        }

        fun setTableMode() {
            savePlayMode(ModeType.TABLE)
        }

        fun setKitchenMode() {
            savePlayMode(ModeType.KITCHEN)
        }

        fun signOut() {
            viewModelScope.launch {
                authUseCase.firebaseSignOut()
                _loginState.value = false
            }
        }

        private fun savePlayMode(mode: ModeType) {
            viewModelScope.launch {
                modeUseCase.savePlayMode(mode)
            }
        }
    }
