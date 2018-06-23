package gr.unipi.msc.android.movieexercise.datamanager;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieDetails;


public class MovieDetailUtils extends JSONUtils<MovieDetails>{

    @Override
    public MovieDetails deserializeModel(String serializedModel) {
        return gson.fromJson(serializedModel, MovieDetails.class);
    }
}
