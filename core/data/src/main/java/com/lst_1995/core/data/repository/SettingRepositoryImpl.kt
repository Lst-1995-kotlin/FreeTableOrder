package com.lst_1995.core.data.repository

import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.lst_1995.core.domain.model.ResultType
import com.lst_1995.core.domain.repository.SettingRepository
import com.lst_1995.core.domain.usecase.Theme
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SettingRepositoryImpl
    @Inject
    constructor(
        private val db: FirebaseFirestore,
        private val auth: FirebaseAuth,
    ) : SettingRepository {
        override suspend fun setTablePassword(password: String): ResultType {
            val map = hashMapOf("password" to password)
            return try {
                auth.uid?.let {
                    val task =
                        db
                            .collection(it)
                            .document("tablePassword")
                            .set(map)
                    task.await()
                    if (task.isSuccessful) {
                        ResultType.SUCCESS
                    } else {
                        ResultType.FAILURE
                    }
                } ?: ResultType.FAILURE
            } catch (e: Exception) {
                ResultType.FAILURE
            }
        }

        override suspend fun saveAppTheme(mode: Int): ResultType {
            val map = hashMapOf("mode" to mode)
            return try {
                auth.uid?.let {
                    val task =
                        db
                            .collection(it)
                            .document("appTheme")
                            .set(map)
                    task.await()
                    if (task.isSuccessful) {
                        ResultType.SUCCESS
                    } else {
                        ResultType.FAILURE
                    }
                } ?: ResultType.FAILURE
            } catch (e: Exception) {
                ResultType.FAILURE
            }
        }

        override fun loadAppThemeFlow(): Flow<Theme> =
            callbackFlow {
                var listener: ListenerRegistration? = null
                try {
                    auth.uid?.let { uid ->
                        listener =
                            db
                                .collection(uid)
                                .document("appTheme")
                                .addSnapshotListener { snapshot, error ->
                                    if (error != null) {
                                        trySend(Theme.SYSTEM)
                                        return@addSnapshotListener
                                    }
                                    val theme =
                                        snapshot?.let {
                                            when (it.getLong("mode")?.toInt()) {
                                                AppCompatDelegate.MODE_NIGHT_NO -> Theme.LIGHT
                                                AppCompatDelegate.MODE_NIGHT_YES -> Theme.DARK
                                                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> Theme.SYSTEM
                                                else -> Theme.SYSTEM
                                            }
                                        } ?: Theme.SYSTEM
                                    trySend(theme)
                                }
                    }
                } catch (e: Exception) {
                    trySend(Theme.SYSTEM)
                }
                awaitClose {
                    listener?.remove()
                }
            }
    }
