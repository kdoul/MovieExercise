package gr.unipi.msc.android.movieexercise.mvp.view;

public interface UserLoginView extends LoadDataView {
    void goToProfile();
    void enableLoginBtn();
    void disableLoginBtn();
    void openTMDBSignUpWebsite();
}
