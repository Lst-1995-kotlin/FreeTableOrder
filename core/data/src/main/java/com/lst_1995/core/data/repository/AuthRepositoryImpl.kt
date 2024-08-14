package com.lst_1995.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.lst_1995.core.data.datasource.LocalDataStore
import com.lst_1995.core.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val localDataStore: LocalDataStore,
        private val auth: FirebaseAuth,
    ) : AuthRepository {
        override fun loginWithGoogle(token: String) {
            Log.d("tttt", "실행")
            auth
                .signInWithCustomToken(token)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("tttt", "성공")
                    } else {
                        Log.d("tttt", "${task.exception}")
                    }

                }
        }

        override fun singupWithGoogle() {
            TODO("Not yet implemented")
        }
    }
