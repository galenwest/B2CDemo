package client.enterprise.b2c.util;

/**
 * Created by raohoulin on 2016.1.11.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
