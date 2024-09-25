package com.lst_1995.core.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.lst_1995.core.domain.util.NetworkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkManagerImpl
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) : NetworkManager {
        override fun networkStateFlow(): Flow<Boolean> =
            callbackFlow {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val callback =
                    object : ConnectivityManager.NetworkCallback() {
                        override fun onAvailable(network: Network) {
                            trySend(true)
                        }

                        override fun onLost(network: Network) {
                            trySend(false)
                        }
                    }

                val request =
                    NetworkRequest
                        .Builder()
                        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        .build()

                val isNetworkAvailable = isInternetAvailable(connectivityManager)
                trySend(isNetworkAvailable)
                connectivityManager.registerNetworkCallback(request, callback)

                awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
            }

        private fun isInternetAvailable(connectivityManager: ConnectivityManager): Boolean {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
    }
