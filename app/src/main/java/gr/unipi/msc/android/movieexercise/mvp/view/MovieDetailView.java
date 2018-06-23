package gr.unipi.msc.android.movieexercise.mvp.view;


public interface MovieDetailView extends LoadDataView {

    void updateBackground(String value);

    void updateTitle(String value);

    void updateYearOfRelease(String value);

    void hideYearOfRelease();

    void updateHomepage(String value);

    void hideHomepage();

    void updateCompanies(String value);

    void hideCompanies();

    void updateTagline(String value);

    void hideTagline();

    void updateOverview(String value);

    void hideOverview();

    void openMovieWebsite(String url);

    void updateToolbarColor();

    void setFABminus();

    void setFABplus();

    void showLoginPrompt();
}