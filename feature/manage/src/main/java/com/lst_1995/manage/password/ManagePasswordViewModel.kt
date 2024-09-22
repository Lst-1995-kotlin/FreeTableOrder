package com.lst_1995.manage.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.model.ResultType
import com.lst_1995.core.domain.usecase.PasswordEvent
import com.lst_1995.core.domain.usecase.PasswordUseCase
import com.lst_1995.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagePasswordViewModel
    @Inject
    constructor(
        private val passwordUseCase: PasswordUseCase,
    ) : BaseViewModel() {
        private val _passwordMessage = MutableLiveData(PasswordEvent.NONE)
        val passwordMessage: LiveData<PasswordEvent> get() = _passwordMessage

        val password = MutableLiveData<String>()
        val passwordCheck = MutableLiveData<String>()

        private fun setTablePassword() {
            viewModelScope.launch {
                if (passwordUseCase.setTablePassword(password.value!!) == ResultType.SUCCESS) {
                    _passwordMessage.value = PasswordEvent.CHANGE_SUCCESS
                }
                setLoadingState(false)
            }
        }

        fun passwordCheck() {
            setLoadingState(true)
            val errorType = passwordUseCase.passwordCheck(password.value, passwordCheck.value)
            if (errorType == PasswordEvent.NONE) {
                setTablePassword()
            } else {
                _passwordMessage.value = errorType
                setLoadingState(false)
            }
        }
    }
