package com.lst_1995.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.domain.usecase.AuthUseCase
import com.lst_1995.core.domain.usecase.ModeUseCase
import com.lst_1995.core.domain.usecase.ThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val authUseCase: AuthUseCase,
        private val modeUseCase: ModeUseCase,
        private val themeUseCase: ThemeUseCase,
    ) : ViewModel() {
        val loginState = authUseCase.loginStateFlow()
        val selectMode = modeUseCase.getPlayModeFlow()
        val theme = themeUseCase.loadAppThemeFlow()

        fun signOut() {
            viewModelScope.launch {
                async {
                    authUseCase.firebaseSignOut()
                    modeUseCase.savePlayMode(ModeType.NONE)
                }.await()
            }
        }
    }
