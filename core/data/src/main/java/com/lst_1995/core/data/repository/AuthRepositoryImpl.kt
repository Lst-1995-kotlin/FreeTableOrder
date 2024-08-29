package com.lst_1995.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lst_1995.core.domain.repository.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val auth: FirebaseAuth,
    ) : AuthRepository {
        override fun firebaseAuthWithGoogle(idToken: String) {
            try {
                auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                Log.d("AuthRepositoryImpl", "${auth.uid}")
            } catch (e: Exception) {
                Log.e("AuthRepositoryImpl", "Firebase auth with Google failed", e)
            }
        }

        override fun loginStateFlow(): Flow<Boolean> =
            callbackFlow {
                val authStateListener =
                    FirebaseAuth.AuthStateListener { auth ->
                        trySend(auth.currentUser != null)
                    }
                auth.addAuthStateListener(authStateListener)
                awaitClose { auth.removeAuthStateListener(authStateListener) }
            }

        override fun firebaseSignOut() {
            auth.signOut()
        }
    }
