package com.lst_1995.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lst_1995.core.data.datasource.LocalDataStore
import com.lst_1995.core.domain.repository.AuthRepository
import com.lst_1995.core.domain.usecase.ResultCode
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val localDataStore: LocalDataStore,
        private val auth: FirebaseAuth,
    ) : AuthRepository {
        override fun firebaseAuthWithGoogle(idToken: String): ResultCode {
            try {
                auth
                    .signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                return ResultCode.SUCCESS
            } catch (e: Exception) {
                Log.e("AuthRepositoryImpl", "Firebase auth with Google failed", e)
                return ResultCode.FAILURE
            }
        }

        override fun autoLoginCheck() = auth.uid != null

        override fun signOut() {
            auth.signOut()
        }
    }
