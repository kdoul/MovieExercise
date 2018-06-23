package gr.unipi.msc.android.movieexercise.mvp.presenter;

import java.util.List;

import gr.unipi.msc.android.movieexercise.MovieExercise;
import gr.unipi.msc.android.movieexercise.datamanager.GetPaginatedUsersMovieWatched;
import gr.unipi.msc.android.movieexercise.datamanager.GetUsersMovieWatched;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.mvp.BasePresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.UserWatchedView;
import gr.unipi.msc.android.movieexercise.util.C;


public class UserWatchedPresenter implements BasePresenter {
    private UserWatchedView view;
    private List<MovieListObject> movies;

    public UserWatchedPresenter(UserWatchedView view, List<MovieListObject> movies) {
        this.view = view;
        this.movies = movies;
    }

    public void loadMoreData(int page){
        if(page>2) {
            MovieExercise.JOB_MANAGER.addJobInBackground(new GetPaginatedUsersMovieWatched(C.API_KEY, MovieExercise.LOCAL_DATA.getUserSessionKey(), page, new GetPaginatedUsersMovieWatched.GetPaginatedUsersMovieWatchedCallback() {
                @Override
                public void onGetPaginatedUsersMovieWatchedCallback(List<MovieListObject> movieList) {
                    addToMovieList(movieList);
                    view.addMovies(movieList);
                }

                @Override
                public void onError(String reason) {
                    view.showFeedback(reason);

                }
            }
            ));
        }
    }

    @Override
    public void createView() {

        hideAllViews();

        view.showLoading();

        downloadMovieList();
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

    private void downloadMovieList(){
        MovieExercise.JOB_MANAGER.addJobInBackground(new GetUsersMovieWatched(C.API_KEY, MovieExercise.LOCAL_DATA.getUserSessionKey(), new GetUsersMovieWatched.GetUsersMovieWatchedCallback() {
            @Override
            public void onGetUsersMovieWatchedCallback(List<MovieListObject> movieList) {
                updateMovieList(movieList);
                view.renderMovies(movieList);

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
