package gr.unipi.msc.android.movieexercise.mvp.presenter;

import android.text.TextUtils;

import gr.unipi.msc.android.movieexercise.MovieExercise;
import gr.unipi.msc.android.movieexercise.datamanager.GetUserAuthToken;
import gr.unipi.msc.android.movieexercise.datamanager.GetUserSessionID;
import gr.unipi.msc.android.movieexercise.datamanager.GetUserSessionToken;
import gr.unipi.msc.android.movieexercise.datamanager.model.UserAuthToken;
import gr.unipi.msc.android.movieexercise.datamanager.model.UserSessionID;
import gr.unipi.msc.android.movieexercise.datamanager.model.UserRequestToken;
import gr.unipi.msc.android.movieexercise.mvp.BasePresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.UserLoginView;
import gr.unipi.msc.android.movieexercise.util.C;


public class UserLoginPresenter implements BasePresenter {

    private UserLoginView view;
    private String apiKey = C.API_KEY;


    public UserLoginPresenter(UserLoginView view) {
        this.view = view;
    }

    public void performLogin(final String username, final String password){
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            view.hideView();
            view.showLoading();
            view.disableLoginBtn();

            MovieExercise.JOB_MANAGER.addJobInBackground(new GetUserAuthToken(apiKey, new GetUserAuthToken.GetUserAuthTokenCalllBack() {
                @Override
                public void onGetUserAuthTokenCallback(final UserAuthToken userAuthToken) {
                    MovieExercise.JOB_MANAGER.addJobInBackground(new GetUserSessionToken(apiKey, userAuthToken.getRequest_token(), username, password, new GetUserSessionToken.GetUserSessionTokenCallBack() {
                        @Override
                        public void onGetUserSessionTokenLoaded(UserRequestToken userRequestToken) {
                            if(userRequestToken.isSuccess()) {
                                MovieExercise.JOB_MANAGER.addJobInBackground(new GetUserSessionID(apiKey, userRequestToken.getRequest_token(), new GetUserSessionID.GetUserSessionIDCallBack() {
                                    @Override
                                    public void onGetUserSessionIDLoaded(UserSessionID userSessionID) {
                                        if(userSessionID.isSuccess()){
                                            MovieExercise.LOCAL_DATA.storeUserSessionKey(userSessionID.getSession_id());
                                            view.showFeedback("Welcome "+username);
                                            view.goToProfile();
                                        }else{
                                            view.hideLoading();
                                            view.showView();
                                            view.showFeedback("Cannot login. There might be a problem with your account.");
                                            view.enableLoginBtn();
                                        }

                                    }

                                    @Override
                                    public void onError(String reason) {
                                        view.hideLoading();
                                        view.showView();
                                        view.showFeedback(reason);
                                        view.enableLoginBtn();
                                    }
                                }));

                            }
                            else {
                                view.hideLoading();
                                view.showView();
                                view.showFeedback("Could not login. There might be a problem with your account.");
                                view.enableLoginBtn();
                            }
                        }

                        @Override
                        public void onError(String reason) {
                            view.hideLoading();
                            view.showView();
                            view.showFeedback(reason);
                            view.enableLoginBtn();
                        }
                    }));
                }

                @Override
                public void onError(String reason) {
                    view.hideLoading();
                    view.showView();
                    view.showFeedback(reason);
                    view.enableLoginBtn();
                }
            }));

        }else{
            view.showFeedback("Please provide a username and a password.");
        }
    }

    @Override
    public void createView() {
        view.hideLoading();
        if(MovieExercise.LOCAL_DATA.hasUserSessionKey())
            view.goToProfile();
    }

    @Override
    public void destroyView() {

    }

    public void onCreateNewAccountClicked() {
        view.openTMDBSignUpWebsite();
    }

    private void hideAllViews() {
        view.hideView();
        view.hideLoading();
    }
}
