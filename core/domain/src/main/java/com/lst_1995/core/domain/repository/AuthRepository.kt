package com.lst_1995.core.domain.repository

interface AuthRepository {
    fun firebaseAuthWithGoogle(idToken: String)

    fun authLoginWithGoogle()
}
