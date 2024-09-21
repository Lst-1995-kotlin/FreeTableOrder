package com.lst_1995.core.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.lst_1995.core.domain.util.NetworkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkManagerImpl
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) : NetworkManager {
        override fun isNetworkAvailable(): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
            return false
        }
    }
