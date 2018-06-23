package gr.unipi.msc.android.movieexercise.datamanager;

import java.io.IOException;
import java.util.List;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetPaginatedDiscoverMovieLists extends BaseAction {

    public interface GetPaginatedDiscoverMovieListsCallback extends BaseCallback {
        void onPaginatedDiscoverMovieListsLoaded(List<MovieListObject> discoverList);
    }

    private String apikey;
    private int page;

    public GetPaginatedDiscoverMovieLists(String apikey,int page, BaseCallback callback) {
        super(callback);
        this.apikey = apikey;
        this.page = page;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().discoverMovies(apikey, page).enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieResults results = response.body();
                    ((GetPaginatedDiscoverMovieListsCallback) callback).onPaginatedDiscoverMovieListsLoaded(results.getResults());

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
