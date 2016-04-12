package client.enterprise.b2c.ui.view;

public interface LoginView {
    public void showProgress();

    public void hideProgress();

    public void setUsernameEmpty();

    public void setPasswordEmpty();

    public void navigateToMine();

    public void showPasswordError();

    public void showUsernameNotFoundError();

    public void showLoginSuccess();

}
