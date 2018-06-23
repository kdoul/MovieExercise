package gr.unipi.msc.android.movieexercise.mvp.presenter;

import android.graphics.Movie;
import android.text.TextUtils;

import gr.unipi.msc.android.movieexercise.MovieExercise;
import gr.unipi.msc.android.movieexercise.datamanager.BaseCallback;
import gr.unipi.msc.android.movieexercise.datamanager.GetMovieDetails;
import gr.unipi.msc.android.movieexercise.datamanager.GetUsersMovieState;
import gr.unipi.msc.android.movieexercise.datamanager.PostWatchlistChange;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieDetails;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieState;
import gr.unipi.msc.android.movieexercise.datamanager.model.PostResponse;
import gr.unipi.msc.android.movieexercise.datamanager.model.ProductionCompany;
import gr.unipi.msc.android.movieexercise.mvp.BasePresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.MovieDetailView;
import gr.unipi.msc.android.movieexercise.util.C;

public class MovieDetailPresenter implements BasePresenter {

    private MovieDetailView view;
    private MovieDetails movieModel;
    private MovieListObject movieListObject;
    private boolean isWatched;
    private String apiKey;

    public MovieDetailPresenter(MovieDetailView view, MovieListObject movieListObject) {
        this.view = view;
        this.movieListObject = movieListObject;
        this.apiKey = C.API_KEY;
    }

    @Override
    public void createView() {
        hideAllViews();
        view.showLoading();

        downloadMovieDetails();

        if(MovieExercise.LOCAL_DATA.hasUserSessionKey()){
            fixWatchlistButton();
        }
    }

    @Override
    public void destroyView() {}

    public void onHomepageClicked() {
        if(!TextUtils.isEmpty(movieModel.getHomepage())) {
            view.openMovieWebsite(movieModel.getHomepage());
        }
    }

    public void onWatchedClick() {
        if(MovieExercise.LOCAL_DATA.hasUserSessionKey()){
            MovieExercise.JOB_MANAGER.addJobInBackground(new PostWatchlistChange(apiKey, MovieExercise.LOCAL_DATA.getUserSessionKey(), movieListObject.getId(), !isWatched, new PostWatchlistChange.PostWatchlistChangeCallback() {
                @Override
                public void onPostWatchlistChangeLoaded(PostResponse result){
                    System.out.println(result.status_message);
                    isWatched = !isWatched;

                    if(isWatched){
                        view.setFABminus();
                    }else{
                        view.setFABplus();
                    }
                }

                @Override
                public void onError(String reason) {
                    view.showFeedback(reason);

                }
            }));


        }else{
            view.showLoginPrompt();
        }
    }

    public void onMainViewScrolled() {
        view.updateToolbarColor();
    }

    private void hideAllViews() {
        view.hideView();
        view.hideLoading();
        view.hideRetry();
        view.hideEmpty();
    }

    private void fixWatchlistButton(){
        MovieExercise.JOB_MANAGER.addJobInBackground(new GetUsersMovieState(apiKey,  MovieExercise.LOCAL_DATA.getUserSessionKey(), movieListObject.getId(), new GetUsersMovieState.GetUsersMovieStateCallback() {
            @Override
            public void onGetUsersMovieStateLoaded(MovieState movieState) {

                isWatched = movieState.isWatchlist();

                if(isWatched){
                    view.setFABminus();
                }else{
                    view.setFABplus();
                }


            }

            @Override
            public void onError(String reason) {
                view.showFeedback(reason);
            }
        }));
    }

    private void downloadMovieDetails() {
        MovieExercise.JOB_MANAGER.addJobInBackground(new GetMovieDetails(apiKey, movieListObject.getId(), new GetMovieDetails.GetMovieDetailsCallback() {
            @Override
            public void onMovieDetailLoaded(MovieDetails movieDetails) {
                updateMovieModel(movieDetails);

                view.updateBackground(movieModel.getBackdrop_path());
                view.updateTitle(movieModel.getOriginal_title());

                if (TextUtils.isEmpty(movieModel.getRelease_date())) {
                    view.hideYearOfRelease();
                } else {
                    view.updateYearOfRelease(movieModel.getRelease_date());
                }

                if (TextUtils.isEmpty(movieModel.getHomepage())) {
                    view.hideHomepage();
                } else {
                    view.updateHomepage(movieModel.getHomepage());
                }

                if (movieModel.getProduction_companies().isEmpty()) {
                    view.hideCompanies();
                } else {
                    StringBuilder companyText = new StringBuilder();
                    for (ProductionCompany productionCompany : movieModel.getProduction_companies()) {
                        companyText.append(productionCompany.getName());
                        companyText.append(", ");
                    }
                    companyText.setLength(companyText.length() - 2 );
                    view.updateCompanies(companyText.toString());
                }

                if (TextUtils.isEmpty(movieModel.getTagline())) {
                    view.hideTagline();
                } else {
                    view.updateTagline(movieModel.getTagline());
                }

                if (TextUtils.isEmpty(movieModel.getOverview())) {
                    view.hideOverview();
                } else {
                    view.updateOverview(movieModel.getOverview());
                }

                view.hideLoading();
                view.showView();
            }

            @Override
            public void onError(String reason) {
                view.showFeedback(reason);
                view.destroyItself();
            }
        }));
    }

    private void updateMovieModel(MovieDetails movieDetails) {
        movieModel = movieDetails;
    }

}
