package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class ModeUseCase
    @Inject
    constructor(
        private val localDataRepository: LocalDataRepository,
    ) {
        suspend fun savePlayMode(mode: ModeType) = localDataRepository.savePlayMode(mode.name)

        fun getPlayModeFlow() = localDataRepository.getPlayModeFlow()
    }
