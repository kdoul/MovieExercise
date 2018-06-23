package gr.unipi.msc.android.movieexercise.datamanager;

import java.io.IOException;
import java.util.List;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetNewMovieLists extends BaseAction {

    public interface GetNewMovieListsCallback extends BaseCallback {
        void onNewMovieListsLoaded(List<MovieListObject> discoverList);
    }

    private String apikey;

    public GetNewMovieLists(String apikey, BaseCallback callback) {
        super(callback);
        this.apikey = apikey;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().getUpcomingMovies(apikey).enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieResults results = response.body();
                    ((GetNewMovieListsCallback) callback).onNewMovieListsLoaded(results.getResults());

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
            public void onFailure(Call<MovieResults> call, Throwable t) {
                errorReason = t.getMessage();
                onCancel(0, t);
            }
        });
    }


}