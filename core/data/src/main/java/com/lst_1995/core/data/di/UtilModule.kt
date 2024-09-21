package com.lst_1995.core.data.di

import com.lst_1995.core.data.util.NetworkManagerImpl
import com.lst_1995.core.domain.util.NetworkManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UtilModule {
    @Binds
    @Singleton
    fun bindsNetworkManager(networkManagerImpl: NetworkManagerImpl): NetworkManager
}
