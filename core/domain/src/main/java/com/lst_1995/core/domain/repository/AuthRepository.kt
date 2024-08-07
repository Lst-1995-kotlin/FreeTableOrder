package com.lst_1995.core.domain.repository

interface AuthRepository {
    fun loginWithGoogle()

    fun singupWithGoogle()

    fun loginWithNaver()

    fun singupWithNaver()

    fun loginWithKakao()

    fun singupWithKakao()
}
