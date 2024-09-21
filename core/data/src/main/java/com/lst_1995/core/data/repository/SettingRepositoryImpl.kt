package com.lst_1995.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.lst_1995.core.domain.model.ResultType
import com.lst_1995.core.domain.repository.SettingRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SettingRepositoryImpl
    @Inject
    constructor(
        private val db: FirebaseFirestore,
        private val fb: FirebaseAuth,
    ) : SettingRepository {
        override suspend fun setTablePassword(password: String): ResultType {
            val map = hashMapOf("password" to password)
            return try {
                fb.uid?.let {
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
    }
