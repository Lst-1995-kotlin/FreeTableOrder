package com.lst_1995.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.lst_1995.core.domain.model.ResultType
import com.lst_1995.core.domain.repository.SettingRepository
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

        override fun saveAppTheme(theme: Int) {
            val map = hashMapOf("theme" to theme)
            auth.uid?.let {
                db
                    .collection(it)
                    .document("appTheme")
                    .set(map)
            }
        }

        override suspend fun loadAppThemeFlow(): Flow<Int> =
            callbackFlow {
                try {
                    val task =
                        auth.uid?.let {
                            db
                                .collection(it)
                                .document("appTheme")
                                .addSnapshotListener { value, _ ->
                                    trySend(
                                        value?.let { value -> value.get("theme") as Int } ?: 5000,
                                    )
                                }
                        }
                    awaitClose { task?.remove() }
                } catch (e: Exception) {
                    trySend(5000)
                }
            }
    }
