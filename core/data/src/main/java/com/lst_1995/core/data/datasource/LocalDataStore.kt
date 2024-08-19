package com.lst_1995.core.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataStore
    @Inject
    constructor(
        private val dataStore: DataStore<Preferences>,
    ) {
        private val playMode = stringPreferencesKey("playMode")

        suspend fun savePlayMode(mode: String) {
            dataStore.edit { preferences -> preferences[playMode] = mode }
        }

        suspend fun getPlayMode(): String = dataStore.data.map { preferences -> preferences[playMode] ?: "" }.first()
    }
