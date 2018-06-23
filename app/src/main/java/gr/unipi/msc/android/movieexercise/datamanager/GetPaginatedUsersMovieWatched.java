package gr.unipi.msc.android.movieexercise.datamanager;

import java.io.IOException;
import java.util.List;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPaginatedUsersMovieWatched extends BaseAction {
    public interface GetPaginatedUsersMovieWatchedCallback extends BaseCallback {
        void onGetPaginatedUsersMovieWatchedCallback(List<MovieListObject> movieList);
    }

    private String apikey;
    private String session_token;
    private int page;

    public GetPaginatedUsersMovieWatched(String apikey, String session_token, int page, BaseCallback callback) {
        super(callback);
        this.apikey = apikey;
        this.session_token = session_token;
        this.page = page;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().getUserWatchList(apikey, session_token, page).enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ((GetPaginatedUsersMovieWatchedCallback) callback).onGetPaginatedUsersMovieWatchedCallback(response.body().getResults());
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
