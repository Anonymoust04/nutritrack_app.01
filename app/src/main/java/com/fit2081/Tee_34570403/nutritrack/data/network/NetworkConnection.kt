package com.fit2081.Tee_34570403.nutritrack.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkConnection(private val context: Context) {

    /**
     * Checks if the device has an active internet connection.
     *
     * Implementation details:
     * - Obtains the ConnectivityManager system service
     * - Queries for active network capabilities
     * - Checks if Wi-Fi, cellular, or ethernet transport is available
     *
     * @return true if the device is connected to the internet, false otherwise
     */
    fun isNetworkAvailable(): Boolean {
        // Get the ConnectivityManager system service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Check if the device has an active network
        val network = connectivityManager.activeNetwork ?: return false
        // Get the network capabilities for the active network
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        // Check if the network has any of the following transports:
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}