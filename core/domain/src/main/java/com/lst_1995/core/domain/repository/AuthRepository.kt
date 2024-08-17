package com.lst_1995.core.domain.repository

import com.lst_1995.core.domain.usecase.ResultCode

interface AuthRepository {
    fun firebaseAuthWithGoogle(idToken: String): ResultCode

    fun autoLoginCheck(): Boolean

    fun signOut()
}
