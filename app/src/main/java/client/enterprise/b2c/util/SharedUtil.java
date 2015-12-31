package client.enterprise.b2c.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by raohoulin on 2015.12.25.
 */
public class SharedUtil {

    //欢迎页相关常量
    private static final String FILE_NAME_WELCOME = "isFirstComing";
    private static final String KEY_NAME_WELCOME = "welcome";
    //欢迎页是否显示的存储
    public static boolean getWelcomeBoolean(Context context) {
        return context.getSharedPreferences(FILE_NAME_WELCOME, Context.MODE_PRIVATE).getBoolean(KEY_NAME_WELCOME, false);
    }
    public static void putWelcomeBoolean(Context context, boolean isFirst) {
        SharedPreferences.Editor editor =  context.getSharedPreferences(FILE_NAME_WELCOME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_NAME_WELCOME, isFirst);
        editor.commit();
    }

}
