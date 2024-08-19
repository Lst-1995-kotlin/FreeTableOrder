package com.lst_1995.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lst_1995.core.domain.model.ResultType
import com.lst_1995.core.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val auth: FirebaseAuth,
    ) : AuthRepository {
        override fun firebaseAuthWithGoogle(idToken: String): ResultType {
            try {
                auth
                    .signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                return ResultType.SUCCESS
            } catch (e: Exception) {
                Log.e("AuthRepositoryImpl", "Firebase auth with Google failed", e)
                return ResultType.FAILURE
            }
        }

        override fun autoLoginCheck() = auth.uid != null

        override fun firebaseSignOut() {
            auth.signOut()
        }
    }
