package com.lst_1995.core.domain.repository

interface AuthRepository {
    fun loginWithGoogle(token: String)

    fun singupWithGoogle()
}
