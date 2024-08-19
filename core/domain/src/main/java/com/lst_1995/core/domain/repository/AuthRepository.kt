package com.lst_1995.core.domain.repository

import com.lst_1995.core.domain.model.ResultType


interface AuthRepository {
    fun firebaseAuthWithGoogle(idToken: String): ResultType

    fun autoLoginCheck(): Boolean

    fun firebaseSignOut()
}
