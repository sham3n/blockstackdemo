package test.blockstack.sammy.service;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.blockstack.sammy.model.NamespaceDetail;
import test.blockstack.sammy.model.user.User;

/**
 * Created by sng on 3/19/18.
 * This Services layer is here and can have differet api services and urls
 */

public class BlockStackServices {
    private static String mCoreApiBaseUrl = "https://core.blockstack.org/v1/";

    private static Retrofit mRetrofit;
    private static CoreApiService mCoreApiService;

    //Use Injection library instead of Singleton
    public BlockStackServices() {
        if(mRetrofit == null) {
            initRetrofit();
        }
    }

    private void initRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        //Add logging
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okhttp3.OkHttpClient.Builder httpClientBuilder = new okhttp3.OkHttpClient
                .Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(mCoreApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClientBuilder.build())
                .build();
        mCoreApiService = mRetrofit.create(CoreApiService.class);
    }

    public void getNamespaces(final Callback<ArrayList<String>> cb) {
        Call<ArrayList<String>> call = mCoreApiService.getNamespaceList();
        call.enqueue(cb);
    }

    public void getNamesForNamespace(@NonNull final String namespace, @NonNull final int page, final Callback<ArrayList<String>> cb) {
        Call<ArrayList<String>> call = mCoreApiService.getNamespaceNames(namespace, page);
        call.enqueue(cb);
    }

    public void getNamespaceDetail(@NonNull final String name, final Callback<NamespaceDetail> cb) {
        Call<NamespaceDetail> call = mCoreApiService.getNameSpaceDetail(name);
        call.enqueue(cb);
    }

    public void getNamespaceHistory(@NonNull final String name, final Callback<Map<String, List<Map<String, String>>>> cb) {
        Call<Map<String, List<Map<String, String>>>> call = mCoreApiService.getNameSpaceHistory(name);
        call.enqueue(cb);
    }

    public void getUserDetail(@NonNull final String name, final Callback<Map<String, User>> cb) {
        Call<Map<String, User>> call = mCoreApiService.getUserDetail(name);
        call.enqueue(cb);
    }
}
