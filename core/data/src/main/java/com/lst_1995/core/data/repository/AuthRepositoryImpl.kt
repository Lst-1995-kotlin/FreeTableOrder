package com.lst_1995.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lst_1995.core.data.datasource.LocalDataStore
import com.lst_1995.core.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val localDataStore: LocalDataStore,
        private val auth: FirebaseAuth,
    ) : AuthRepository {
        override fun firebaseAuthWithGoogle(idToken: String) { // 레포지토리 쪽으로 이동
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth
                .signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                    } else {
                    }
                }
        }

        override fun authLoginWithGoogle() {
            // TODO("Not yet implemented")
        }
    }
