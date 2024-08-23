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

        suspend fun getPlayMode(): ModeType {
            return when(localDataRepository.getPlayMode()) {
                ModeType.MANAGE.name -> ModeType.MANAGE
                ModeType.TABLE.name -> ModeType.TABLE
                ModeType.KITCHEN.name -> ModeType.KITCHEN
                else -> ModeType.MANAGE
            }
        }
    }
