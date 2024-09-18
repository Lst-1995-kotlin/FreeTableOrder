package com.lst_1995.manage.password

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.usecase.PasswordErrorType
import com.lst_1995.core.domain.usecase.SettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagePasswordViewModel
    @Inject
    constructor(
        private val settingUseCase: SettingUseCase,
    ) : ViewModel() {
        private val _passwordMessage = MutableLiveData(PasswordErrorType.NONE)
        val passwordMessage: LiveData<PasswordErrorType> get() = _passwordMessage

        private val _complete = MutableLiveData(false)
        val complete: LiveData<Boolean> get() = _complete

        val password = MutableLiveData<String>()
        val passwordCheck = MutableLiveData<String>()
        val loadingProgressVisibility = MutableLiveData(View.GONE)

        private fun setTablePassword() {
            viewModelScope.launch {
                loadingProgressVisibility.value = View.VISIBLE
                settingUseCase.setTablePassword(password.value!!)
                loadingProgressVisibility.value = View.GONE
                _complete.value = true
            }
        }

        fun passwordCheck() {
            val errorType = settingUseCase.passwordCheck(password.value, passwordCheck.value)
            if (errorType == PasswordErrorType.NONE) setTablePassword()
            _passwordMessage.value = errorType
        }
    }
