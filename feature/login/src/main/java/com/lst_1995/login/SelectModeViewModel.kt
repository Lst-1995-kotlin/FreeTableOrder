package com.lst_1995.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.domain.usecase.AuthUseCase
import com.lst_1995.core.domain.usecase.ModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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

        private val _selectMode = MutableLiveData<ModeType>()
        val selectMode: LiveData<ModeType> get() = _selectMode

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
                async {
                    authUseCase.firebaseSignOut()
                    deleteMode()
                }.await()
                loginCheck()
            }
        }

        private fun deleteMode() {
            savePlayMode(ModeType.NONE)
        }

        private fun loginCheck() {
            if (!authUseCase.autoLoginCheck()) _loginState.value = false
        }

        private fun savePlayMode(mode: ModeType) {
            viewModelScope.launch {
                async {
                    modeUseCase.savePlayMode(mode)
                }.await()
                _selectMode.value = modeUseCase.getPlayMode()
            }
        }
    }
