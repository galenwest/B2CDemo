package client.enterprise.b2c.model.bean.bo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by raohoulin on 2016.1.20.
 */
public class LoginBuffer implements Parcelable {

    public static final String LOGIN_SUCCESS = "0";
    public static final String SUCCEED_HAS_PHONE = "1";
    public static final String SUCCEED_NO_PHONE = "2";
    public static final String ERROR_USERNAME_IS_NULL = "3";
    public static final String ERROR_PASSWORD_IS_NULL = "4";
    public static final String PASSWORD_ERROR = "5";
    public static final String ERROR_USERNAME_NOT_FOUND = "6";

    private String statue;
    private String userinfo;

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.statue);
        dest.writeString(this.userinfo);
    }

    public LoginBuffer() {
    }

    protected LoginBuffer(Parcel in) {
        this.statue = in.readString();
        this.userinfo = in.readString();
    }

    public static final Creator<LoginBuffer> CREATOR = new Creator<LoginBuffer>() {
        public LoginBuffer createFromParcel(Parcel source) {
            return new LoginBuffer(source);
        }

        public LoginBuffer[] newArray(int size) {
            return new LoginBuffer[size];
        }
    };
}
