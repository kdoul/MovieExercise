package gr.unipi.msc.android.movieexercise.datamanager.model;

public class UserSessionID {
    private boolean success;
    private String session_id;

    public boolean isSuccess() {
        return success;
    }

    public String getSession_id() {
        return session_id;
    }

    public UserSessionID(boolean success, String session_id) {

        this.success = success;
        this.session_id = session_id;
    }
}
