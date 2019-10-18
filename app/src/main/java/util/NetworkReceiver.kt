package util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

import views.MainActivity

/**
 * Broadcast receiver for the ConnectivityManager.
 */
class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(activity: Context, intent: Intent) {

        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

        //Update the UI for Network Availability
        if (activity is MainActivity) {
            activity.updateUIForNetworkAvailability(isConnected)
        }
    }

}
