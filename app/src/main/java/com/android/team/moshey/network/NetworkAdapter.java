package com.android.team.moshey.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.team.moshey.utils.ConstantUtils.MPESA_BASE_URL;

/**
 * Created By blackcoder
 * On 20/04/19
 **/
public abstract class NetworkAdapter {
    private static final Object LOCK = new Object();
    private static volatile Retrofit sInstance;
    private static HttpLoggingInterceptor mInterceptor;

    public static Retrofit getRetrofitInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    setHttpLoggingInterceptor();
                    sInstance = new Retrofit.Builder()
                            .baseUrl(MPESA_BASE_URL)
                            .client(getOkhttpBuild())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return sInstance;
    }

    private static OkHttpClient getOkhttpBuild() {
        return new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private static void setHttpLoggingInterceptor() {
        mInterceptor = new HttpLoggingInterceptor();
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
