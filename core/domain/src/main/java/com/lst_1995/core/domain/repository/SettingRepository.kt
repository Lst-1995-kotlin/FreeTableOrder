package com.lst_1995.core.domain.repository

interface SettingRepository {
    suspend fun setTablePassword(password: String)
}
