package com.lst_1995.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.lst_1995.core.domain.repository.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl
    @Inject
    constructor(
        private val db: FirebaseFirestore,
        private val fb: FirebaseAuth,
    ) : SettingRepository {
        override suspend fun setTablePassword(password: String) {
            val map = hashMapOf("password" to password)
            fb.uid?.let {
                db
                    .collection(it)
                    .document("tablePassword")
                    .set(map)
            }
        }
    }
