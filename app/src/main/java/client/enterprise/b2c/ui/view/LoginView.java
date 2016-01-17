/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package client.enterprise.b2c.ui.view;

public interface LoginView {
    public void showProgress();

    public void hideProgress();

    public void setUsernameEmpty();

    public void setPasswordEmpty();

    public void navigateToMine();

    public void showUsernameOrPasswordError();

    public void showServerError();

    public void showConntctTimeOutError();

    public void showResponseTimeOutError();

    public void showLoginError();

    public void showLoginSuccess();

}
