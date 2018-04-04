package test.blockstack.sammy.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Apps {

    @SerializedName("http://publik.ykliao.com")
    @Expose
    private String httpPublikYkliaoCom;
    @SerializedName("https://app.graphitedocs.com")
    @Expose
    private String httpsAppGraphitedocsCom;
    @SerializedName("https://www.chat.hihermes.co")
    @Expose
    private String httpsWwwChatHihermesCo;
    @SerializedName("https://www.stealthy.im")
    @Expose
    private String httpsWwwStealthyIm;

    public String getHttpPublikYkliaoCom() {
        return httpPublikYkliaoCom;
    }

    public void setHttpPublikYkliaoCom(String httpPublikYkliaoCom) {
        this.httpPublikYkliaoCom = httpPublikYkliaoCom;
    }

    public String getHttpsAppGraphitedocsCom() {
        return httpsAppGraphitedocsCom;
    }

    public void setHttpsAppGraphitedocsCom(String httpsAppGraphitedocsCom) {
        this.httpsAppGraphitedocsCom = httpsAppGraphitedocsCom;
    }

    public String getHttpsWwwChatHihermesCo() {
        return httpsWwwChatHihermesCo;
    }

    public void setHttpsWwwChatHihermesCo(String httpsWwwChatHihermesCo) {
        this.httpsWwwChatHihermesCo = httpsWwwChatHihermesCo;
    }

    public String getHttpsWwwStealthyIm() {
        return httpsWwwStealthyIm;
    }

    public void setHttpsWwwStealthyIm(String httpsWwwStealthyIm) {
        this.httpsWwwStealthyIm = httpsWwwStealthyIm;
    }

}