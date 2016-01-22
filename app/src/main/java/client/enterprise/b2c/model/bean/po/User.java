package client.enterprise.b2c.model.bean.po;

import java.io.Serializable;

/**
 * Created by raohoulin on 2016.1.19.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -3755260782673837466L;

    private String Signature;
    private int ID;
    private String U_CName;
    private String U_Pwd;
    private String U_RegeistDate;
    private String U_Tel;
    private String U_UImg;
    private int U_Type;
    private String U_Status;
    private String U_ClientNum;
    private String U_VIPNum;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getU_ClientNum() {
        return U_ClientNum;
    }

    public void setU_ClientNum(String u_ClientNum) {
        U_ClientNum = u_ClientNum;
    }

    public String getU_CName() {
        return U_CName;
    }

    public void setU_CName(String u_CName) {
        U_CName = u_CName;
    }

    public String getU_Pwd() {
        return U_Pwd;
    }

    public void setU_Pwd(String u_Pwd) {
        U_Pwd = u_Pwd;
    }

    public String getU_RegeistDate() {
        return U_RegeistDate;
    }

    public void setU_RegeistDate(String u_RegeistDate) {
        U_RegeistDate = u_RegeistDate;
    }

    public String getU_Status() {
        return U_Status;
    }

    public void setU_Status(String u_Status) {
        U_Status = u_Status;
    }

    public String getU_Tel() {
        return U_Tel;
    }

    public void setU_Tel(String u_Tel) {
        U_Tel = u_Tel;
    }

    public int getU_Type() {
        return U_Type;
    }

    public void setU_Type(int u_Type) {
        U_Type = u_Type;
    }

    public String getU_UImg() {
        return U_UImg;
    }

    public void setU_UImg(String u_UImg) {
        U_UImg = u_UImg;
    }

    public String getU_VIPNum() {
        return U_VIPNum;
    }

    public void setU_VIPNum(String u_VIPNum) {
        U_VIPNum = u_VIPNum;
    }
}
