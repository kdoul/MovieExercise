package gr.unipi.msc.android.movieexercise.datamanager.model;


public class UserRequestToken {
    private final boolean success;
    private final String request_token;

    public boolean isSuccess() {
        return success;
    }

    public String getRequest_token() {
        return request_token;
    }

    public UserRequestToken(boolean success, String request_token) {

        this.success = success;
        this.request_token = request_token;
    }
}
