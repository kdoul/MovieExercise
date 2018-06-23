package gr.unipi.msc.android.movieexercise.mvp.presenter;

import java.util.List;

import gr.unipi.msc.android.movieexercise.MovieExercise;
import gr.unipi.msc.android.movieexercise.datamanager.GetDiscoverMovieLists;
import gr.unipi.msc.android.movieexercise.datamanager.GetNewMovieLists;
import gr.unipi.msc.android.movieexercise.datamanager.GetPaginatedNewMovieLists;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.mvp.BasePresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.NewMoviesView;
import gr.unipi.msc.android.movieexercise.util.C;


public class NewMoviesPresenter implements BasePresenter {

    private NewMoviesView view;
    private List<MovieListObject> movies;

    public NewMoviesPresenter(NewMoviesView view, List<MovieListObject> movies) {
        this.view = view;
        this.movies = movies;
    }

    public void loadMoreData(int page){
        MovieExercise.JOB_MANAGER.addJobInBackground(new GetPaginatedNewMovieLists(C.API_KEY, page, new GetPaginatedNewMovieLists.GetPaginatedNewMovieListsCallback() {

            @Override
            public void onPaginatedNewMovieListsLoaded(List<MovieListObject> discoverList) {
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
        MovieExercise.JOB_MANAGER.addJobInBackground(new GetNewMovieLists(C.API_KEY, new GetNewMovieLists.GetNewMovieListsCallback() {

            @Override
            public void onNewMovieListsLoaded(List<MovieListObject> discoverList) {

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
