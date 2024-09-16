package com.lst_1995.manage.password

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.usecase.SettingUseCase
import com.lst_1995.manage.R
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
        val loadingProgressVisibility = MutableLiveData<Int>(View.GONE)

        private fun setTablePassword(password: String) {
            viewModelScope.launch {
                async {
                    loadingProgressVisibility.value = View.VISIBLE
                    settingUseCase.setTablePassword(password)
                    loadingProgressVisibility.value = View.GONE
                }.await()
            }
        }

        fun passwordCheck() {
            when {
                password.value.isNullOrEmpty() ->
                    _passwordSetMessage.value = R.string.password_input.toString()

                passwordCheck.value.isNullOrEmpty() ->
                    _passwordSetMessage.value = R.string.password_input_check.toString()

                password.value!! != passwordCheck.value!! ->
                    _passwordSetMessage.value = R.string.password_not_match.toString()

                password.value!!.length < 4 ->
                    _passwordSetMessage.value =
                        R.string.password_length_check.toString()

                password.value!!.contains("\n") ->
                    _passwordSetMessage.value =
                        R.string.password_newline_check.toString()

                password.value!!.contains(" ") ->
                    _passwordSetMessage.value =
                        R.string.password_space_check.toString()

                else -> {
                    _passwordSetMessage.value = ""
                    setTablePassword(password.value!!)
                }
            }
        }
    }
