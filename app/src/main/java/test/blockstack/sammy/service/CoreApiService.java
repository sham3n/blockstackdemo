package test.blockstack.sammy.service;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import test.blockstack.sammy.model.NamespaceDetail;
import test.blockstack.sammy.model.user.User;

/**
 * This ApiService connects directly to the Core API system to retrieve info
 * Created by sng on 3/19/18.
 */

public interface CoreApiService {

    @NonNull
    @GET("namespaces")
    Call<ArrayList<String>> getNamespaceList();

    @NonNull
    @GET("namespaces/{tld}/names")
    Call<ArrayList<String>> getNamespaceNames(@NonNull @Path("tld") String namespace, @Query(value = "page") int page);


    @NonNull
    @GET("names/{nameId}")
    Call<NamespaceDetail> getNameSpaceDetail(@NonNull @Path("nameId") String namespaceId);

    @NonNull
    @GET("names/{nameId}/history")
    Call<Map<String, List<Map<String, String>>>> getNameSpaceHistory(@NonNull @Path("nameId") String namespaceId);

    @NonNull
    @GET("users/{username}")
    Call<Map<String, User>> getUserDetail(@NonNull @Path("username") String username);
}
