package com.lst_1995.core.domain.util

import kotlinx.coroutines.flow.Flow

interface NetworkManager {
    fun networkStateFlow(): Flow<Boolean>
}
