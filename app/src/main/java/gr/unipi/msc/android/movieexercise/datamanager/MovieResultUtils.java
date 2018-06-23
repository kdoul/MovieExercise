package gr.unipi.msc.android.movieexercise.datamanager;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;

public class MovieResultUtils extends JSONUtils<MovieListObject> {
    @Override
    public MovieListObject deserializeModel(String serializedModel) {
        return gson.fromJson(serializedModel, MovieListObject.class);
    }
}
