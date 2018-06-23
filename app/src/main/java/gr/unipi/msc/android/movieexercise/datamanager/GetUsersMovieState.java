package gr.unipi.msc.android.movieexercise.datamanager;


import java.io.IOException;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieState;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUsersMovieState extends BaseAction {

    public interface GetUsersMovieStateCallback extends BaseCallback{
        void onGetUsersMovieStateLoaded(MovieState movieState);
    }

    private String apikey;
    private String user_session_key;
    private int movieID;

    public GetUsersMovieState(String apikey, String user_session_key, int movieID, BaseCallback callback) {
        super(callback);
        this.apikey = apikey;
        this.user_session_key = user_session_key;
        this.movieID = movieID;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().getMovieState(movieID, apikey, user_session_key).enqueue(new Callback<MovieState>() {
            @Override
            public void onResponse(Call<MovieState> call, Response<MovieState> response) {
                if(response.isSuccessful() && response.body() != null){
                    ((GetUsersMovieStateCallback) callback).onGetUsersMovieStateLoaded(response.body());
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
            public void onFailure(Call<MovieState> call, Throwable t) {
                errorReason = t.getMessage();
                onCancel(0, t);
            }
        });
    }
}
