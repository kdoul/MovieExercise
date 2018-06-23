package gr.unipi.msc.android.movieexercise.datamanager;

import java.io.IOException;

import gr.unipi.msc.android.movieexercise.datamanager.model.UserRequestToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetUserSessionToken extends BaseAction {

    public GetUserSessionToken(String apikey,String requestToken, String username, String password, BaseCallback callback) {
        super(callback);

        this.apikey = apikey;
        this.username = username;
        this.password = password;
        this.requestToken = requestToken;
    }

    public interface GetUserSessionTokenCallBack extends BaseCallback {
        void onGetUserSessionTokenLoaded(UserRequestToken userRequestToken);
    }

    private String apikey;
    private String username;
    private String password;
    private String requestToken;

    @Override
    public void onRun() throws Throwable {
        API.http().getUserSessionToken(apikey, requestToken, username, password).enqueue(new Callback<UserRequestToken>() {
            @Override
            public void onResponse(Call<UserRequestToken> call, Response<UserRequestToken> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserRequestToken results = response.body();
                    ((GetUserSessionTokenCallBack) callback).onGetUserSessionTokenLoaded(results);
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
            public void onFailure(Call<UserRequestToken> call, Throwable t) {
                errorReason = t.getMessage();
                onCancel(0, t);
            }
        });
    }
}
