package gr.unipi.msc.android.movieexercise.mvp.presenter;

import android.text.TextUtils;

import java.util.List;

import gr.unipi.msc.android.movieexercise.MovieExercise;
import gr.unipi.msc.android.movieexercise.datamanager.GetSearchResultMovieLists;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.mvp.BasePresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.SearchMoviesView;
import gr.unipi.msc.android.movieexercise.util.C;

public class SearchMoviesPresenter implements BasePresenter {

    private SearchMoviesView view;
    private String apiKey;
    private String lastQuery = "";

    public SearchMoviesPresenter(SearchMoviesView view) {
        this.view = view;
        this.apiKey = C.API_KEY;
    }

    @Override
    public void createView() {
        hideAllViews();
//        view.showLoading();

    }

    @Override
    public void destroyView() {
        view.cleanTimer();
    }

    public void performSearch(String query) {
        if(!TextUtils.isEmpty(query) && !lastQuery.equals(query.trim())) { // avoid blank searches and consecutive repeated searches

            lastQuery = query.trim(); // store the last query

            view.hideView();
            view.showLoading();

            MovieExercise.JOB_MANAGER.addJobInBackground(new GetSearchResultMovieLists(apiKey, lastQuery, new GetSearchResultMovieLists.GetSearchResultMovieListsCallBack() {
                @Override
                public void onGetSearchResultMovieListsCallBack(List<MovieListObject> discoverList) {
                    view.hideLoading();
                    view.renderMoviesList(discoverList);
                    view.showView();
                }

                @Override
                public void onError(String reason) {
                    view.hideLoading();
                    view.removeMoviesList();
                    view.showFeedback(reason);
                    lastQuery = "";
                }
            }));
        }
    }

    private void hideAllViews() {
        view.hideView();
        view.hideLoading();
    }
//
//    private void checkIfHasTheBaseImageURL() {
//        if(!MovieExercise.LOCAL_DATA.hasBaseImageURL()) {
//            MovieExercise.JOB_MANAGER.addJobInBackground(new GetImageConfigurationUseCase(apiKey, new GetImageConfigurationUseCase.GetImageConfigurationUseCaseCallback() {
//                @Override
//                public void onConfigurationDownloaded(ConfigurationEntity configurationEntity) {
//                    TMDb.LOCAL_DATA.storeBaseImageURL(configurationEntity.getBase_url());
//
//                    showEmptyMovies();
//                }
//
//                @Override
//                public void onError(String reason) {
//                    view.hideLoading();
//                    view.showView();
//                    view.showFeedback(reason);
//                }
//            }));
//
//        } else {
//            showEmptyMovies();
//        }
//    }

    private void showEmptyMovies() {
        view.hideLoading();
        view.removeMoviesList();
        view.showView();
    }
}
