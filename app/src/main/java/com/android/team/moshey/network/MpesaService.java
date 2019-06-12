package com.android.team.moshey.network;

import com.android.team.moshey.models.entities.mpesa.AuthResponse;
import com.android.team.moshey.utils.ConstantUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created By blackcoder
 * On 20/04/19
 **/
public interface MpesaService {

    @Headers({
            "Cache-Control: no-cache"
    })
    @GET("generate?grant_type=client_credentials")
    Call<AuthResponse> getAuthToken(@Header("authorization") String authCredentials);

}
