package com.lst_1995.manage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.usecase.SettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagePasswordViewModel
    @Inject
    constructor(
        private val settingUseCase: SettingUseCase,
    ) : ViewModel() {
        private val _passwordSetMessage = MutableLiveData<String>()
        val passwordSetMessage: LiveData<String> get() = _passwordSetMessage

        val password = MutableLiveData<String>()
        val passwordCheck = MutableLiveData<String>()

        private fun setTablePassword(password: String) {
            viewModelScope.launch {
                async {
                    settingUseCase.setTablePassword(password)
                }.await()
                _passwordSetMessage.value = "비밀번호가 설정되었습니다."
            }
        }

        fun passwordCheck() {
            when {
                password.value == null || password.value!!.isEmpty() ->
                    _passwordSetMessage.value =
                        "비밀번호를 입력해주세요."

                passwordCheck.value == null || passwordCheck.value!!.isEmpty() ->
                    _passwordSetMessage.value =
                        "비밀번호 확인을 입력해주세요."

                password.value!! != passwordCheck.value!! -> _passwordSetMessage.value = "비밀번호가 일치하지 않습니다."
                password.value!!.length < 4 -> _passwordSetMessage.value = "비밀번호는 4자리 이상이어야 합니다."
                password.value!!.contains("\n") -> _passwordSetMessage.value = "비밀번호에 줄바꿈을 사용할 수 없습니다."
                password.value!!.contains(" ") -> _passwordSetMessage.value = "비밀번호에 공백을 사용할 수 없습니다."
                else -> setTablePassword(password.value!!)
            }
        }
    }
