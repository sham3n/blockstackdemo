package test.blockstack.sammy.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ZoneFile {

    @SerializedName("$origin")
    @Expose
    private String $origin;
    @SerializedName("$ttl")
    @Expose
    private Integer $ttl;
    @SerializedName("uri")
    @Expose
    private List<Map<String, String>> uri = null;

    public String get$origin() {
        return $origin;
    }

    public void set$origin(String $origin) {
        this.$origin = $origin;
    }

    public Integer get$ttl() {
        return $ttl;
    }

    public void set$ttl(Integer $ttl) {
        this.$ttl = $ttl;
    }

    public List<Map<String, String>> getUri() {
        return uri;
    }

    public void setUri(List<Map<String, String>> uri) {
        this.uri = uri;
    }

}