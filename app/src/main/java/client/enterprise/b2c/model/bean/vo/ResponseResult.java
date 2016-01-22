package client.enterprise.b2c.model.bean.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by raohoulin on 2016.1.20.
 */
public class ResponseResult implements Parcelable {

    public static final String NORESULT = "0";
    public static final String ERROR = "2";
    public static final String SUCCESS = "1";
    public static final String TOKENERROR = "403";
    public static final String CHECK = "3";

    protected String requestCode;
    protected String result;

    public ResponseResult(String requestCode, String result) {
        this.requestCode = requestCode;
        this.result = result;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.requestCode);
        dest.writeString(this.result);
    }

    public ResponseResult() {
    }

    protected ResponseResult(Parcel in) {
        this.requestCode = in.readString();
        this.result = in.readString();
    }

    public static final Parcelable.Creator<ResponseResult> CREATOR = new Parcelable.Creator<ResponseResult>() {
        public ResponseResult createFromParcel(Parcel source) {
            return new ResponseResult(source);
        }

        public ResponseResult[] newArray(int size) {
            return new ResponseResult[size];
        }
    };
}
