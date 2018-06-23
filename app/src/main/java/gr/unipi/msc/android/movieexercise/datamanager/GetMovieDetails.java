package gr.unipi.msc.android.movieexercise.datamanager;

import java.io.IOException;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieDetails;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetMovieDetails extends BaseAction {

    public interface GetMovieDetailsCallback extends BaseCallback {
        void onMovieDetailLoaded(MovieDetails movieDetails);
    }

    private int movieID;
    private String apiKey;

    public GetMovieDetails(String apiKey, int movieID, BaseCallback callback) {
        super(callback);
        this.movieID = movieID;
        this.apiKey = apiKey;
    }


    @Override
    public void onRun() throws Throwable {
        API.http().movieDetails(movieID,apiKey).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieDetails results = response.body();
                    ((GetMovieDetailsCallback) callback).onMovieDetailLoaded(results);

                } else {
                    try {
                        errorReason = response.errorBody().string();
                    } catch (IOException e) {
                        errorReason = e.getMessage();
                    }
                    onCancel(response.code(), null);
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                errorReason = t.getMessage();
                onCancel(0, t);
            }
        });
    }
}
