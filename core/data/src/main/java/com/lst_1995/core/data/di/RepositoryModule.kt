package com.lst_1995.core.data.di

import com.lst_1995.core.data.repository.AuthRepositoryImpl
import com.lst_1995.core.data.repository.LocalDataRepositoryImpl
import com.lst_1995.core.data.repository.SettingRepositoryImpl
import com.lst_1995.core.domain.repository.AuthRepository
import com.lst_1995.core.domain.repository.LocalDataRepository
import com.lst_1995.core.domain.repository.SettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindsLocalDataRepository(localDataRepositoryImpl: LocalDataRepositoryImpl): LocalDataRepository

    @Binds
    @Singleton
    fun bindsSettingRepository(settingRepositoryImpl: SettingRepositoryImpl): SettingRepository
}
