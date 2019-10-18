package model

import androidx.lifecycle.MutableLiveData
import network.ApiConnectionModule
import network.ResponseCodes
import pojo.ApiResponse
import pojo.ApiResponseDataSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException
import java.util.*

// API Service class - @return Livedata object, which will be observed in the view.
class ProfileModelAPIService(val liveData: MutableLiveData<ApiResponseDataSet>) {


    fun makeNetworkCall() {
        val headMap = HashMap<String, String>()
        headMap["Content-Type"] = "application/json"

        val responseCallback = ApiConnectionModule.instance.getProfile(headMap)
        responseCallback.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val apiResponse = response.body()
                liveData.value = ApiResponseDataSet(apiResponse, ResponseCodes.SUCCESS)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                if (t is UnknownHostException) {

                    liveData.value = ApiResponseDataSet(
                        null,
                        ResponseCodes.NETWORK_ERROR
                    )
                } else {
                    liveData.value = ApiResponseDataSet(
                        null,
                        ResponseCodes.FAILURE
                    )
                }
            }
        })
    }
}
