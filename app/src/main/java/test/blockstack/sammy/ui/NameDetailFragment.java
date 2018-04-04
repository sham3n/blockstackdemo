package test.blockstack.sammy.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.blockstack.sammy.R;
import test.blockstack.sammy.manager.Datamanager;
import test.blockstack.sammy.model.NamespaceDetail;
import test.blockstack.sammy.model.user.User;
import test.blockstack.sammy.uihandler.NameDetailUiHandler;

/**
 * This class gets the Users profile info which includes: User Profile, Name history, and name details
 * Use the {@link NameDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NameDetailFragment extends Fragment {
    private static final String NAME_ID_PARAM = "nameParam";

    private String mNameIdParam;

    //This handler handles UI updates
    private NameDetailUiHandler mNameDetailUiHander;

    @BindView(R.id.name_detail_id)
    TextView mNameId;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NameDetailFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param nameId Name Id to get info for
     * @return A new instance of fragment NameDetailFragment.
     */
    public static NameDetailFragment newInstance(@Nullable String nameId) {
        NameDetailFragment fragment = new NameDetailFragment();
        Bundle args = new Bundle();
        args.putString(NAME_ID_PARAM, nameId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNameIdParam = getArguments().getString(NAME_ID_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_name_detail, container, false);
        ButterKnife.bind(this, view);
        mNameDetailUiHander = new NameDetailUiHandler(view);

        //Only request data
        if(mNameIdParam != null) {
            mNameId.setText(mNameIdParam);
            getNameInfo();
            getUserProfile();
            getNamespaceHistory();
        }
        return view;
    }

    private void getNameInfo() {
        Datamanager.getInstance().getNamespaceDetails(mNameIdParam, new Callback<NamespaceDetail>() {
            @Override
            public void onResponse(Call<NamespaceDetail> call, Response<NamespaceDetail> response) {
                NamespaceDetail namespaceDetail = response.body();
                mNameDetailUiHander.handleNameInfoUpdate(getContext(), namespaceDetail);
            }

            @Override
            public void onFailure(Call<NamespaceDetail> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getUserProfile() {
        Datamanager.getInstance().getUserDetails(mNameIdParam, new Callback<Map<String, User>>() {
            @Override
            public void onResponse(Call<Map<String, User>> call, Response<Map<String, User>> response) {
                User user = response.body().get(mNameIdParam);
                mNameDetailUiHander.handleUserInfoUpdate(getContext(), user);
            }

            @Override
            public void onFailure(Call<Map<String, User>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getNamespaceHistory() {
        Datamanager.getInstance().getNamespaceHistory(mNameIdParam, new Callback<Map<String, List<Map<String, String>>>>() {
            @Override
            public void onResponse(Call<Map<String, List<Map<String, String>>>> call, Response<Map<String, List<Map<String, String>>>> response) {
                Map<String, List<Map<String, String>>> history = response.body();
                mNameDetailUiHander.handleNamespaceHistory(getContext(), history);
            }

            @Override
            public void onFailure(Call<Map<String, List<Map<String, String>>>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
