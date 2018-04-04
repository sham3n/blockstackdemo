package test.blockstack.sammy.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.blockstack.sammy.R;
import test.blockstack.sammy.manager.Datamanager;

/**
 * A fragment representing the different namespaces and list of the names of the namespaces
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 * TODO Cache, Pagination, Loading Indicator, Error Handing, flow navigation, etc
 */
public class NamespacesFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    @BindView(R.id.namespace_names_list)
    RecyclerView mNamespaceNamesList;
    @BindView(R.id.namespace_list)
    TabLayout mNamespaceTabs;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NamespacesFragment() {
    }

    public static NamespacesFragment newInstance() {
        NamespacesFragment fragment = new NamespacesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_namespaces, container, false);
        ButterKnife.bind(this, view);
        // Set the namespace list adapter
        mNamespaceNamesList.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration recycleViewDivider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recycleViewDivider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.recycleview_item_divider));
        mNamespaceNamesList.addItemDecoration(recycleViewDivider);

        //Set up the tab
        mNamespaceTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getNamesForNamespace(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getNamespaces();
    }

    private void getNamespaces() {
        Datamanager.getInstance().getNamespaces(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                //Loop through the namespaces and add the tabs
                for (String namespace : response.body()) {
                    mNamespaceTabs.addTab(mNamespaceTabs.newTab().setText(namespace));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getNamesForNamespace(@NonNull String namespace) {
        Datamanager.getInstance().getNamesForNamespaces(namespace, 0, new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                mNamespaceNamesList.setAdapter(new BlockStackRecyclerViewAdapter(response.body(), mListener));
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String item);
    }
}
