package gr.unipi.msc.android.movieexercise.mvp.view;

import java.util.List;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;


public interface SearchMoviesView extends LoadDataView {

    void renderMoviesList(List<MovieListObject> movies);
    void removeMoviesList();

    void cleanTimer();
}
