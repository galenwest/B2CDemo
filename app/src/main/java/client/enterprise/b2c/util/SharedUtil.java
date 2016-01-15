package client.enterprise.b2c.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by raohoulin on 2015.12.25.
 */
public class SharedUtil {

    // 欢迎页相关常量
    private static final String FILE_NAME_WELCOME = "isFirstComing";
    private static final String KEY_NAME_WELCOME = "activity_welcome";
    // 账户存储相关常量
    private static final String FILE_NAME_USER_INFO = "UserInfo";
    private static final String KEY_NAME_USERNAME = "username";
    private static final String KEY_NAME_PASSWORD = "password";

    // 欢迎页是否显示的存储
    public static boolean getWelcomeBoolean(Context context) {
        return context.getSharedPreferences(FILE_NAME_WELCOME, Context.MODE_PRIVATE).getBoolean(KEY_NAME_WELCOME, false);
    }
    public static void putWelcomeBoolean(Context context, boolean isFirst) {
        SharedPreferences.Editor editor =  context.getSharedPreferences(FILE_NAME_WELCOME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_NAME_WELCOME, isFirst);
        editor.commit();
    }

    // 获取账户名
    public static String getUsername(Context context) {
        return context.getSharedPreferences(FILE_NAME_USER_INFO, Context.MODE_PRIVATE).getString(KEY_NAME_USERNAME, "");
    }
    // 获取账户密码
    public static String getPassword(Context context) {
        return context.getSharedPreferences(FILE_NAME_USER_INFO, Context.MODE_PRIVATE).getString(KEY_NAME_PASSWORD, "");
    }
    // 账户名的存储
    public static void putUsername(Context context, String username) {
        SharedPreferences.Editor editor =  context.getSharedPreferences(FILE_NAME_USER_INFO, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_NAME_USERNAME, username);
        editor.commit();
    }
    // 账户密码的存储
    public static void putPassword(Context context, String password) {
        SharedPreferences.Editor editor =  context.getSharedPreferences(FILE_NAME_USER_INFO, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_NAME_PASSWORD, password);
        editor.commit();
    }

}
