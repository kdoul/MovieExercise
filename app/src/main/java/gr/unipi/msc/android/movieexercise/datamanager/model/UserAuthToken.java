package gr.unipi.msc.android.movieexercise.datamanager.model;


public class UserAuthToken {
    private final boolean success;
    private final String expires_at;
    private final String request_token;

    public boolean isSuccess() {
        return success;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public String getRequest_token() {
        return request_token;
    }

    public UserAuthToken(boolean success, String expires_at, String request_token) {

        this.success = success;
        this.expires_at = expires_at;
        this.request_token = request_token;
    }
}
