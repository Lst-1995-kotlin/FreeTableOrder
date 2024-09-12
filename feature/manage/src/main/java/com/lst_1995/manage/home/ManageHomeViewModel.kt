package com.lst_1995.manage.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.domain.usecase.ModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageHomeViewModel
    @Inject
    constructor(
        private val modeUseCase: ModeUseCase,
    ) : ViewModel() {
        val loginState = modeUseCase.getPlayModeFlow()

        fun changeMode() {
            viewModelScope.launch {
                modeUseCase.savePlayMode(ModeType.NONE)
            }
        }
    }
