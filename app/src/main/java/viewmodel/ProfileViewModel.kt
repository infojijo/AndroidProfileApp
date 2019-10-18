package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import model.ProfileModelAPIService
import pojo.ApiResponseDataSet

/**
 * ViewModel class provides the ProfileData
 */
class ProfileViewModel : ViewModel() {

    var profileLiveData = MutableLiveData<ApiResponseDataSet>()

    fun getProfileData(isNetworkConnected: Boolean) {
        if (isNetworkConnected) {
             ProfileModelAPIService(profileLiveData).makeNetworkCall()
        }
    }
}
