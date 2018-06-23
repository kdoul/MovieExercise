package gr.unipi.msc.android.movieexercise.datamanager;

import java.io.IOException;

import gr.unipi.msc.android.movieexercise.datamanager.model.UserAuthToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetUserAuthToken extends BaseAction {

    public interface GetUserAuthTokenCalllBack extends BaseCallback {
        void onGetUserAuthTokenCallback(UserAuthToken userAuthToken);
    }

    private String apiKey;

    public GetUserAuthToken(String apiKey, BaseCallback callback) {
        super(callback);
        this.apiKey = apiKey;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().getUserAuthToken(apiKey).enqueue(new Callback<UserAuthToken>() {
            @Override
            public void onResponse(Call<UserAuthToken> call, Response<UserAuthToken> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserAuthToken results = response.body();
                    ((GetUserAuthTokenCalllBack) callback).onGetUserAuthTokenCallback(results);
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
            public void onFailure(Call<UserAuthToken> call, Throwable t) {
                errorReason = t.getMessage();
                onCancel(0, t);
            }
        });
    }
}
