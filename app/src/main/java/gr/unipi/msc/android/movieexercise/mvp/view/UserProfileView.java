package gr.unipi.msc.android.movieexercise.mvp.view;

public interface UserProfileView extends LoadDataView {

    void updateUsername(String username);
    void updateID(int id);
    void updateName(String name);
    void updateGravatar(String hash);

    void goToLoginPage();

    void goToUserWatched();
}
