package test.blockstack.sammy.uihandler;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import test.blockstack.sammy.R;
import test.blockstack.sammy.model.NamespaceDetail;
import test.blockstack.sammy.model.user.Account;
import test.blockstack.sammy.model.user.Profile;
import test.blockstack.sammy.model.user.User;

/**
 * This class is used to handle majority of the UI updates to separate the concerns from the fragment
 * Created by sng on 3/20/18.
 */

public class NameDetailUiHandler {
    @BindView(R.id.namespace_detail_layout)
    LinearLayout mNamespaceDetailLayout;

    @BindView(R.id.name_detail_owner)
    TextView mOwner;
    @BindView(R.id.name_detail_value_hash)
    TextView mValueHash;
    @BindView(R.id.name_detail_expires)
    TextView mExpires;
    @BindView(R.id.name_detail_blockchain)
    TextView mBlockchain;
    @BindView(R.id.name_detail_last_txid)
    TextView mLastTxid;
    @BindView(R.id.name_detail_zone_file)
    TextView mZoneFile;

    //profile details
    @BindView(R.id.user_profile_layout)
    LinearLayout mUserProfileLayout;
    @BindView(R.id.user_profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.user_profile_description)
    TextView mProfileDescription;
    @BindView(R.id.user_profile_fullname)
    TextView mProfileName;

    public NameDetailUiHandler( View view) {

        ButterKnife.bind(this, view);
    }

    public void handleNameInfoUpdate(Context context, NamespaceDetail namespaceDetail) {
        mOwner.setText(context.getString(R.string.owner_hash, namespaceDetail.getZonefileHash()));
        mBlockchain.setText(context.getString(R.string.blockchain, namespaceDetail.getBlockChain()));
        mExpires.setText(context.getString(R.string.expire, namespaceDetail.getExpireBlock()));
        mLastTxid.setText(context.getString(R.string.last_txid, namespaceDetail.getLastTxId()));
        if(mZoneFile.getText().length() == 0)
            mZoneFile.setText(context.getString(R.string.zone_file, namespaceDetail.getZonefile()));
    }

    public void handleUserInfoUpdate(Context context, User user) {
        //If error is not there, then make user profile visible
        //TODO add more error checking user-cases
        if(TextUtils.isEmpty(user.getError()) && user.getProfile() != null) {
            mUserProfileLayout.setVisibility(View.VISIBLE);

            Profile profile = user.getProfile();
            if(profile.getImage() != null & profile.getImage().size() > 0) {
                Picasso.with(context).load(profile.getImage().get(0).getContentUrl()).fit().into(mProfileImage);
            }

            mProfileDescription.setText(profile.getDescription());
            mProfileName.setText(profile.getName());

            List<Account> accounts = profile.getAccount();
            if(accounts != null) {
                for(Account account : accounts) {
                    addNewTextView(context, mUserProfileLayout, account.getService() + ": " + account.getIdentifier());
                }
            }

            if(user.getZoneFile() != null && user.getZoneFile().getUri() != null && user.getZoneFile().getUri().size() > 0) {
                mZoneFile.setText(context.getString(R.string.zone_file, user.getZoneFile().getUri().get(0).toString()));
            }
        }
    }

    public void handleNamespaceHistory(Context context, Map<String, List<Map<String, String>>> history) {
        //This is a hack for the test demo, there are a lot more elegant solutions to this
        for(String key : history.keySet()) {
            Map<String, String> historyMap = history.get(key).get(0);
            //get collapsible layout
            final ViewGroup layout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.collapsible_row, mNamespaceDetailLayout, false);
            TextView textView = layout.findViewById(R.id.block_title);
            textView.setText("BLOCK #" + key + " (" + historyMap.get("opcode") + ")");

            StringBuilder builder = new StringBuilder();
            for(String historyKey : historyMap.keySet()) {
                builder.append("\r\n").append(historyKey).append(": \r\n").append(historyMap.get(historyKey));
            }

            //Set description
            TextView description = layout.findViewById(R.id.description);
            description.setText(builder.toString());

            //set listener
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View expandView = layout.findViewById(R.id.expandView);
                    final TextView toggleView = layout.findViewById(R.id.toggle);
                    boolean shouldExpand = expandView.getVisibility() == View.GONE;

                    if (shouldExpand) {
                        expandView.setVisibility(View.VISIBLE);
                        toggleView.setText(R.string.collapse);
                    } else {
                        expandView.setVisibility(View.GONE);
                        toggleView.setText(R.string.expand);
                    }
                }
            });

            mNamespaceDetailLayout.addView(layout);
        }
    }

    private void addNewTextView(Context context, ViewGroup layout, String text) {
        //Normally I would do this with a custom view, but not enough time
        TextView textView = new TextView(context);
        textView.setTextSize(18);
        textView.setPadding(10, 10, 10, 10);
        textView.setText(text);
        textView.setBackgroundResource(R.drawable.gray_border_bottom);
        layout.addView(textView);
    }
}
