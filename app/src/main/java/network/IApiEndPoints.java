package network;

import java.util.Map;

import pojo.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

// Api endpoints defined here
public interface IApiEndPoints {

    @GET("aboutme/mycv.php")
    Call<ApiResponse> getProfile(@HeaderMap Map<String, String> map);

}
