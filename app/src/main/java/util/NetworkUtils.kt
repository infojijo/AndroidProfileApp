@file:Suppress("DEPRECATION")

package util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

import views.MainActivity

object NetworkUtils {

    /**
     * @param activity
     * @return whether app has network connectivity or not
     */

    fun isNetworkConnected(activity: Activity?): Boolean {
        if (activity == null) return false

        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

        //Update the UI for Network Availability
        if (activity is MainActivity) {
            activity.updateUIForNetworkAvailability(isConnected)
        }
        return isConnected
    }
}