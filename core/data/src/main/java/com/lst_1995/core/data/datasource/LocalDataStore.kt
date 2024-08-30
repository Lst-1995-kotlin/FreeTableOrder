package com.lst_1995.core.data.datasource

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.lst_1995.core.domain.model.ModeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataStore
    @Inject
    constructor(
        private val dataStore: DataStore<Preferences>,
    ) {
        private val playMode = stringPreferencesKey("playMode")
        private val theme = intPreferencesKey("theme")

        suspend fun savePlayMode(mode: String) {
            dataStore.edit { preferences -> preferences[playMode] = mode }
        }

        fun getPlayModeFlow(): Flow<String> =
            dataStore.data.map { preferences ->
                preferences[playMode] ?: ModeType.NONE.name
            }

        suspend fun saveThemeMode(mode: Int) {
            dataStore.edit { preferences -> preferences[theme] = mode }
        }

        fun getThemeModeFlow(): Flow<Int> =
            dataStore.data.map { preferences ->
                preferences[theme]?.toInt() ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
    }
