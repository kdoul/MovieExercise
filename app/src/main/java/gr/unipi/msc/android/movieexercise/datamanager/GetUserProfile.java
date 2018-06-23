package gr.unipi.msc.android.movieexercise.datamanager;


import java.io.IOException;

import gr.unipi.msc.android.movieexercise.datamanager.model.UserProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserProfile extends BaseAction{

    public interface GetUserProfileCallback extends BaseCallback{
        void onGetUserProfileCallbackLoaded(UserProfile userProfile);
    }
    private String apikey;
    private String session_key;

    public GetUserProfile(String apikey, String session_key, BaseCallback callback) {
        super(callback);
        this.apikey = apikey;
        this.session_key = session_key;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().getUserProfile(apikey, session_key).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful() && response.body() != null){
                    ((GetUserProfileCallback) callback).onGetUserProfileCallbackLoaded(response.body());
                }else{
                    try {
                        errorReason = response.errorBody().string();
                    } catch (IOException e) {
                        errorReason = e.getMessage();
                    }
                    onCancel(response.code(), null);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                errorReason = t.getMessage();
                onCancel(0, t);
            }
        });

    }
}
