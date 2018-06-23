package gr.unipi.msc.android.movieexercise.mvp.presenter;

import java.util.List;

import gr.unipi.msc.android.movieexercise.MovieExercise;
import gr.unipi.msc.android.movieexercise.datamanager.GetPaginatedPopularMovieLists;
import gr.unipi.msc.android.movieexercise.datamanager.GetPopularMovieLists;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.mvp.BasePresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.PopularMoviesView;
import gr.unipi.msc.android.movieexercise.util.C;


public class PopularMoviesPresenter implements BasePresenter {

    private PopularMoviesView view;
    private List<MovieListObject> movies;

    public PopularMoviesPresenter(PopularMoviesView view, List<MovieListObject> movies) {
        this.view = view;
        this.movies = movies;
    }

    public void loadMoreData(int page){
        MovieExercise.JOB_MANAGER.addJobInBackground(new GetPaginatedPopularMovieLists(C.API_KEY, page, new GetPaginatedPopularMovieLists.GetPaginatedPopularMovieListsCallback(){

            @Override
            public void onPaginatedPopularMovieListsLoaded(List<MovieListObject> discoverList) {
                addToMovieList(discoverList);
                view.addMovies(discoverList);
//                view.renderMovies(discoverList);

//                view.hideLoading();
//                view.showView();
            }


            @Override
            public void onError(String reason) {
                view.showFeedback(reason);
//                view.destroyItself();
            }
        }));
    }

    @Override
    public void createView() {

        hideAllViews();

        view.showLoading();

        downloadDiscoverMovieList();
//        if(movies.isEmpty()) {
//            view.showEmpty("There are no movies to show");
//            view.hideLoading();
//        } else {
//            view.renderMovies(movies);
//            view.hideLoading();
//            view.showView();
//        }
    }

    @Override
    public void destroyView() {}

    private void hideAllViews() {
        view.hideView();
        view.hideEmpty();
        view.hideLoading();
        view.hideRetry();
    }

    private void downloadDiscoverMovieList(){
        MovieExercise.JOB_MANAGER.addJobInBackground(new GetPopularMovieLists(C.API_KEY, new GetPopularMovieLists.GetPopularMovieListsCallBack() {

            @Override
            public void onGetPopularMovieListsCallBack(List<MovieListObject> discoverList) {

                updateMovieList(discoverList);
                view.renderMovies(discoverList);

                view.hideLoading();
                view.showView();
            }

            @Override
            public void onError(String reason) {
                view.showFeedback(reason);
            }
        }));
    }

    private void addToMovieList(List<MovieListObject> newData){
        this.movies.addAll(newData);
    }

    private void updateMovieList(List<MovieListObject> movies){
        this.movies = movies;
    }
}
