package test.blockstack.sammy.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sng on 3/19/18.
 */

public class NamespaceDetail {
    @NonNull
    @SerializedName("address")
    private String mAddress;
    @NonNull
    @SerializedName("blockchain")
    private String mBlockChain;
    @Nullable
    @SerializedName("expire_block")
    private int mExpireBlock;
    @NonNull
    @SerializedName("last_txid")
    private String mLastTxId;
    @NonNull
    @SerializedName("status")
    private String mStatus;
    @NonNull
    @SerializedName("zonefile")
    protected String mZonefile;
    @NonNull
    @SerializedName("zonefile_hash")
    private String mZonefileHash;

    protected NamespaceDetail(@NonNull NamespaceDetail namespaceDetail) {
        mAddress = namespaceDetail.getAddress();
        mBlockChain = namespaceDetail.getBlockChain();
        mExpireBlock = namespaceDetail.getExpireBlock();
        mLastTxId = namespaceDetail.getLastTxId();
        mStatus = namespaceDetail.getStatus();
        mZonefile = namespaceDetail.getZonefile();
        mZonefileHash = namespaceDetail.getZonefileHash();
    }

    @NonNull
    public String getAddress() {
        return mAddress;
    }

    @NonNull
    public String getBlockChain() {
        return mBlockChain;
    }

    @Nullable
    public int getExpireBlock() {
        return mExpireBlock;
    }

    @NonNull
    public String getLastTxId() {
        return mLastTxId;
    }

    @NonNull
    public String getStatus() {
        return mStatus;
    }

    @NonNull
    public String getZonefile() {
        return mZonefile;
    }

    @NonNull
    public String getZonefileHash() {
        return mZonefileHash;
    }
}
