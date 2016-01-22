package client.enterprise.b2c.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by raohoulin on 2016.1.20.
 */
public class Check {

    public static boolean checkPhone(String phonenumber)
    {
        String phone = "0\\d{2,3}-\\d{7,8}";
        Pattern p = Pattern.compile(phone);
        Matcher m = p.matcher(phonenumber);
        return m.matches();
    }
}
