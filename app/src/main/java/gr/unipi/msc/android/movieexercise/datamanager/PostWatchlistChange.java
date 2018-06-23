package gr.unipi.msc.android.movieexercise.datamanager;

import android.support.v4.util.ArrayMap;

import org.json.JSONObject;

import java.util.Map;

import gr.unipi.msc.android.movieexercise.datamanager.model.PostResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostWatchlistChange extends BaseAction{
    private String apikey, user_session_key;
    private int movieID;
    private boolean statusToSet;

    public interface PostWatchlistChangeCallback extends BaseCallback {
        void onPostWatchlistChangeLoaded(PostResponse result);
    }

    public PostWatchlistChange(String apikey, String user_session_key, int movieID,boolean statusToSet, BaseCallback callback) {
        super(callback);
        this.apikey = apikey;
        this.user_session_key = user_session_key;
        this.movieID = movieID;
        this.statusToSet = statusToSet;
    }

    @Override
    public void onRun() throws Throwable {
        Map<String, Object> jsonParams = new ArrayMap<>();
//put something inside the map, could be null
        jsonParams.put("media_type", "movie");
        jsonParams.put("media_id", movieID);
        jsonParams.put("watchlist", statusToSet);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json"),(new JSONObject(jsonParams)).toString());
        System.err.println(body.contentLength());
        System.err.println(new JSONObject(jsonParams).toString());
        API.http().postToUserWatchList(apikey, user_session_key, body).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                ((PostWatchlistChangeCallback)callback).onPostWatchlistChangeLoaded(response.body());
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                errorReason = t.getMessage();
                onCancel(0, t);
            }
        });
    }
}
