package gr.unipi.msc.android.movieexercise.datamanager;


import java.io.IOException;
import java.util.List;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUsersMovieWatched extends BaseAction{

    public interface GetUsersMovieWatchedCallback extends BaseCallback{
        void onGetUsersMovieWatchedCallback(List<MovieListObject> movieList);
    }

    private String apikey;
    private String session_token;

    public GetUsersMovieWatched(String apikey, String session_token, BaseCallback callback) {
        super(callback);
        this.apikey = apikey;
        this.session_token = session_token;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().getUserWatchList(apikey, session_token).enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful() && response.body() != null){
                    ((GetUsersMovieWatchedCallback)callback).onGetUsersMovieWatchedCallback(response.body().getResults());
                }else {
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
