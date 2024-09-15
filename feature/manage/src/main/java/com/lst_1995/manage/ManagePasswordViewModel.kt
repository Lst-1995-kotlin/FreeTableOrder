package com.lst_1995.manage

import androidx.lifecycle.ViewModel
import com.lst_1995.core.domain.usecase.SettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManagePasswordViewModel
    @Inject
    constructor(
        private val settingUseCase: SettingUseCase,
    ) : ViewModel() {
        fun setTablePassword(password: String) {
            settingUseCase.setTablePassword(password)
        }
    }
