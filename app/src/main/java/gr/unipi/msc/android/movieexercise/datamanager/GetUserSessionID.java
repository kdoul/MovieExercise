package gr.unipi.msc.android.movieexercise.datamanager;


import java.io.IOException;

import gr.unipi.msc.android.movieexercise.datamanager.model.UserSessionID;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserSessionID extends BaseAction {

    public GetUserSessionID(String apikey,String requestToken, BaseCallback callback) {
        super(callback);

        this.apikey = apikey;
        this.requestToken = requestToken;
    }

    public interface GetUserSessionIDCallBack extends BaseCallback {
        void onGetUserSessionIDLoaded(UserSessionID userSessionID);
    }

    private String apikey;

    private String requestToken;

    @Override
    public void onRun() throws Throwable {
        API.http().getUserSessionID(apikey, requestToken).enqueue(new Callback<UserSessionID>() {
            @Override
            public void onResponse(Call<UserSessionID> call, Response<UserSessionID> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserSessionID results = response.body();
                    ((GetUserSessionIDCallBack) callback).onGetUserSessionIDLoaded(results);
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
            public void onFailure(Call<UserSessionID> call, Throwable t) {
                errorReason = t.getMessage();
                onCancel(0, t);
            }
        });
    }
}
