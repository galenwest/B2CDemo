package client.enterprise.b2c.model.bean.bo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by raohoulin on 2016.1.23.
 */
public class LoginOutBuffer implements Parcelable {

    public static final String LOGIN_OUT_SUCCESS = "1";
    public static final String LOGIN_OUT_ERROR = "2";
    public static final String LOGIN_OUT_SUCCESS_OLD = "3";
    public static final String SOME_ERROR = "4";

    private String Statue;
    private String Tip;

    public String getStatue() {
        return Statue;
    }

    public void setStatue(String statue) {
        Statue = statue;
    }

    public String getTip() {
        return Tip;
    }

    public void setTip(String tip) {
        Tip = tip;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Statue);
        dest.writeString(this.Tip);
    }

    public LoginOutBuffer() {
    }

    protected LoginOutBuffer(Parcel in) {
        this.Statue = in.readString();
        this.Tip = in.readString();
    }

    public static final Parcelable.Creator<LoginOutBuffer> CREATOR = new Parcelable.Creator<LoginOutBuffer>() {
        public LoginOutBuffer createFromParcel(Parcel source) {
            return new LoginOutBuffer(source);
        }

        public LoginOutBuffer[] newArray(int size) {
            return new LoginOutBuffer[size];
        }
    };
}
