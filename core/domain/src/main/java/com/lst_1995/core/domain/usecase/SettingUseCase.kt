package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.repository.SettingRepository
import javax.inject.Inject

class SettingUseCase
    @Inject
    constructor(
        private val settingRepository: SettingRepository,
    ) {
        suspend fun setTablePassword(password: String) = settingRepository.setTablePassword(password)
    }
