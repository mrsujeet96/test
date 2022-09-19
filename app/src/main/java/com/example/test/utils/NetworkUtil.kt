package com.example.test.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkUtil {

    var connectivityManger: ConnectivityManager? = null;
    var isConnected = false

    companion object {
        private val instance = NetworkUtil()
        var context: Context? = null
        fun getInstance(context: Context): NetworkUtil {
            this.context = context.applicationContext
            return instance
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(): Boolean {
        connectivityManger =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManger != null) {

            var capabilities =
                connectivityManger?.getNetworkCapabilities(connectivityManger?.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    isConnected = true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    isConnected = true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    isConnected = true
                }
            }
        }

        return isConnected
    }
}