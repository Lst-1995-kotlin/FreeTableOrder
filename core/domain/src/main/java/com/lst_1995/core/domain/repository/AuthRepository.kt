package com.lst_1995.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun firebaseAuthWithGoogle(idToken: String)

    fun loginStateFlow(): Flow<Boolean>

    fun firebaseSignOut()
}
