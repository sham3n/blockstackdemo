package test.blockstack.sammy.manager;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;
import test.blockstack.sammy.model.NamespaceDetail;
import test.blockstack.sammy.model.user.User;
import test.blockstack.sammy.service.BlockStackServices;

/**
 * Created by sng on 3/19/18.
 * The datamanager layer allows manipulation of data before requesting data
 */

public class Datamanager {

    private static Datamanager mInstance;

    private BlockStackServices mCoreServices;

    //Use Injection library instead of Singleton
    public static Datamanager getInstance() {
        if(mInstance == null) {
            mInstance = new Datamanager();
        }

        return mInstance;
    }

    private Datamanager() {
        mCoreServices = new BlockStackServices();
    }

    public void getNamespaces(final Callback<ArrayList<String>> cb) {
        mCoreServices.getNamespaces(cb);
    }

    public void getNamesForNamespaces(@NonNull final String namespace, @NonNull final int page, final Callback<ArrayList<String>> cb) {
        mCoreServices.getNamesForNamespace(namespace, page, cb);
    }

    public void getNamespaceDetails(@NonNull final String name, final Callback<NamespaceDetail> cb) {
        mCoreServices.getNamespaceDetail(name, cb);
    }

    public void getNamespaceHistory(@NonNull final String name, final Callback<Map<String, List<Map<String, String>>>> cb) {
        mCoreServices.getNamespaceHistory(name, cb);
    }

    public void getUserDetails(@NonNull final String name, final Callback<Map<String, User>> cb) {
        mCoreServices.getUserDetail(name, cb);
    }
}
