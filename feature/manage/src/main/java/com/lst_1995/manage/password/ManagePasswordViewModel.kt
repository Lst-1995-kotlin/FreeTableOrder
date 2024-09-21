package com.lst_1995.manage.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.usecase.PasswordEvent
import com.lst_1995.core.domain.usecase.SettingUseCase
import com.lst_1995.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagePasswordViewModel
    @Inject
    constructor(
        private val settingUseCase: SettingUseCase,
    ) : BaseViewModel() {
        private val _passwordMessage = MutableLiveData(PasswordEvent.NONE)
        val passwordMessage: LiveData<PasswordEvent> get() = _passwordMessage

        private val _complete = MutableLiveData(false)
        val complete: LiveData<Boolean> get() = _complete

        val password = MutableLiveData<String>()
        val passwordCheck = MutableLiveData<String>()

        private fun setTablePassword() {
            viewModelScope.launch {
                settingUseCase.setTablePassword(password.value!!)
                setLoadingState(false)
                _complete.value = true
            }
        }

        fun passwordCheck() {
            setLoadingState(true)
            val errorType = settingUseCase.passwordCheck(password.value, passwordCheck.value)
            if (errorType == PasswordEvent.NONE) {
                setTablePassword()
            } else {
                _passwordMessage.value = errorType
                setLoadingState(false)
            }
        }
    }
