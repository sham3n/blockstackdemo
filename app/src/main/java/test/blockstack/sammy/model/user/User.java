package test.blockstack.sammy.model.user;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("owner_address")
    @Expose
    private String ownerAddress;
    @SerializedName("profile")
    @Expose
    private Profile profile;
    @SerializedName("public_key")
    @Expose
    private String publicKey;
    @SerializedName("verifications")
    @Expose
    private List<Verification> verifications = null;
    @SerializedName("zone_file")
    @Expose
    private ZoneFile zoneFile;

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public List<Verification> getVerifications() {
        return verifications;
    }

    public void setVerifications(List<Verification> verifications) {
        this.verifications = verifications;
    }

    public ZoneFile getZoneFile() {
        return zoneFile;
    }

    public void setZoneFile(ZoneFile zoneFile) {
        this.zoneFile = zoneFile;
    }

    @Nullable
    public String getError() {
        return error;
    }
}