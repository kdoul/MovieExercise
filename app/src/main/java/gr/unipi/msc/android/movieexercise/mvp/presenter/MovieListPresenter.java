package gr.unipi.msc.android.movieexercise.mvp.presenter;

import java.util.List;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.mvp.BasePresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.MovieListView;


public class MovieListPresenter implements BasePresenter {

    private MovieListView view;
    private List<MovieListObject> movies;

    public MovieListPresenter(MovieListView view, List<MovieListObject> movies) {
        this.view = view;
        this.movies = movies;
    }

    public void loadMoreData(int page){
//        MovieExercise.JOB_MANAGER.addJobInBackground(new GetPaginatedDiscoverMovieLists(C.API_KEY, page, new GetPaginatedDiscoverMovieLists.GetPaginatedDiscoverMovieListsCallback() {
//
//            @Override
//            public void onPaginatedDiscoverMovieListsLoaded(List<MovieListObject> discoverList) {
//                addToMovieList(discoverList);
//                view.addMovies(discoverList);
////                view.renderMovies(discoverList);
//
////                view.hideLoading();
////                view.showView();
//            }
//
//            @Override
//            public void onError(String reason) {
//                view.showFeedback(reason);
////                view.destroyItself();
//            }
//        }));
    }

    @Override
    public void createView() {

        hideAllViews();

        view.showLoading();

//        downloadDiscoverMovieList();
        if(movies.isEmpty()) {
            view.showEmpty("There are no movies to show");
            view.hideLoading();
        } else {
            view.renderMovies(movies);
            view.hideLoading();
            view.showView();
        }
    }

    @Override
    public void destroyView() {}

    private void hideAllViews() {
        view.hideView();
        view.hideEmpty();
        view.hideLoading();
        view.hideRetry();
    }

//    private void downloadDiscoverMovieList(){
//        MovieExercise.JOB_MANAGER.addJobInBackground(new GetDiscoverMovieLists(C.API_KEY, new GetDiscoverMovieLists.GetDiscoverMovieListsCallback() {
//
//            @Override
//            public void onDiscoverMovieListsLoaded(List<MovieListObject> discoverList) {
//
//                updateMovieList(discoverList);
//                view.renderMovies(discoverList);
//
//                view.hideLoading();
//                view.showView();
//            }
//
//            @Override
//            public void onError(String reason) {
//                view.showFeedback(reason);
//                view.destroyItself();
//            }
//        }));
//    }

    private void addToMovieList(List<MovieListObject> newData){
        this.movies.addAll(newData);
    }

    private void updateMovieList(List<MovieListObject> movies){
        this.movies = movies;
    }
}
