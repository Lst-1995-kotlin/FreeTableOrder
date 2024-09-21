package com.lst_1995.core.domain.repository

import com.lst_1995.core.domain.model.ResultType

interface SettingRepository {
    suspend fun setTablePassword(password: String): ResultType
}
