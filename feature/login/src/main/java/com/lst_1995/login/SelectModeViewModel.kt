package com.lst_1995.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectModeViewModel
    @Inject
    constructor(
        private val userUseCase: UserUseCase,
    ) : ViewModel() {
        fun savePlayMode(mode: String) {
            viewModelScope.launch {
                userUseCase.savePlayMode(mode)
            }
        }
        fun logOut() {

        }
    }
