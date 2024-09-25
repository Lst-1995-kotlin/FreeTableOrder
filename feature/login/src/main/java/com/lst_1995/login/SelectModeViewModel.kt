package com.lst_1995.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.domain.usecase.AuthUseCase
import com.lst_1995.core.domain.usecase.ModeUseCase
import com.lst_1995.core.domain.usecase.Theme
import com.lst_1995.core.domain.usecase.ThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectModeViewModel
    @Inject
    constructor(
        private val modeUseCase: ModeUseCase,
        private val authUseCase: AuthUseCase,
        private val themeUseCase: ThemeUseCase,
    ) : ViewModel() {
        val loginState: Flow<Boolean> = authUseCase.loginStateFlow()
        val selectMode: Flow<String> = modeUseCase.getPlayModeFlow()
        val themeState: Flow<Theme> = themeUseCase.loadAppThemeFlow()

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
            }
        }

        private fun deleteMode() {
            savePlayMode(ModeType.NONE)
        }

        private fun savePlayMode(mode: ModeType) {
            viewModelScope.launch {
                modeUseCase.savePlayMode(mode)
            }
        }
    }
