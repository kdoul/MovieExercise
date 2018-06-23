package gr.unipi.msc.android.movieexercise.mvp.presenter;

import gr.unipi.msc.android.movieexercise.MovieExercise;
import gr.unipi.msc.android.movieexercise.datamanager.GetUserProfile;
import gr.unipi.msc.android.movieexercise.datamanager.model.UserProfile;
import gr.unipi.msc.android.movieexercise.mvp.BasePresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.UserProfileView;
import gr.unipi.msc.android.movieexercise.util.C;


public class UserProfilePresenter implements BasePresenter{

    private UserProfileView view;
    private String apikey;
    private UserProfile userProfile;
    private String session_key;

    public UserProfilePresenter(UserProfileView view) {
        this.view = view;
        this.apikey = C.API_KEY;
        this.session_key = MovieExercise.LOCAL_DATA.getUserSessionKey();
    }

    @Override
    public void createView() {
        if(!MovieExercise.LOCAL_DATA.hasUserSessionKey())
            view.goToLoginPage();
        else {
            hideAllViews();
            view.showLoading();

            loadProfile();
        }
    }

    @Override
    public void destroyView() {

    }

    public void onLogoutClicked(){
        hideAllViews();
        MovieExercise.LOCAL_DATA.deleteUserSessionKey();
        view.goToLoginPage();
    }

    public void onGoToWatchedClicked(){
        view.goToUserWatched();


//        view.hideView();
//        view.showLoading();
//
//        MovieExercise.JOB_MANAGER.addJobInBackground(new GetUsersMovieWatched(apikey, session_key, new GetUsersMovieWatched.GetUsersMovieWatchedCallback() {
//            @Override
//            public void onGetUsersMovieWatchedCallback(List<MovieListObject> movieList) {
//                view.hideLoading();
//
//            }
//
//            @Override
//            public void onError(String reason) {
//                view.hideLoading();
//                view.showView();
//                view.showFeedback(reason);
//            }
//        }));
    }

    private void loadProfile(){
        MovieExercise.JOB_MANAGER.addJobInBackground(new GetUserProfile(apikey, session_key, new GetUserProfile.GetUserProfileCallback() {
            @Override
            public void onGetUserProfileCallbackLoaded(UserProfile userProfile) {
                updateProfile(userProfile);
                view.updateName(userProfile.getName());
                view.updateID(userProfile.getID());
                view.updateUsername(userProfile.getUsername());
                view.updateGravatar(userProfile.getAvatar().getGravatar().getHash());

                view.hideLoading();
                view.showView();
            }

            @Override
            public void onError(String reason) {
                view.showFeedback(reason);
            }
        }));
    }

    private void updateProfile(UserProfile userProfile){
        this.userProfile = userProfile;
    }

    private void hideAllViews() {
        view.hideView();
        view.hideLoading();
        view.hideRetry();
        view.hideEmpty();
    }
}
