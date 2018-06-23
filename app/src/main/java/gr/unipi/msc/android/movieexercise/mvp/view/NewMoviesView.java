package gr.unipi.msc.android.movieexercise.mvp.view;

import java.util.List;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;


public interface NewMoviesView extends LoadDataView {

    void renderMovies(List<MovieListObject> movies);
    void addMovies(List<MovieListObject> movies);
    void clearMovies();

}