package test.blockstack.sammy.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("placeholder")
    @Expose
    private Boolean placeholder;
    @SerializedName("proofType")
    @Expose
    private String proofType;
    @SerializedName("proofUrl")
    @Expose
    private String proofUrl;
    @SerializedName("service")
    @Expose
    private String service;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Boolean getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Boolean placeholder) {
        this.placeholder = placeholder;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public String getProofUrl() {
        return proofUrl;
    }

    public void setProofUrl(String proofUrl) {
        this.proofUrl = proofUrl;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}