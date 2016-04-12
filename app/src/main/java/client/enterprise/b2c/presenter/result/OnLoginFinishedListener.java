package client.enterprise.b2c.presenter.result;

public interface OnLoginFinishedListener {

    public void onUsernameEmpty();

    public void onPasswordEmpty();

    public void onLoginSuccess(String statue, String responseString);

    public void onLoginError(String statue, String responseString);

    public void hideDialog();
}
