package gr.unipi.msc.android.movieexercise.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesController {

    public static final String TAG = SharedPreferencesController.class.getSimpleName();
    public static final int PRIVATE_MODE = 0;

    public static final String USER_SESSION_KEY = "USER_SESSION_KEY";

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesController(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(TAG, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }


    public void storeUserSessionKey(String key) {
        System.out.println("KEY TO STORE IS "+key);
        editor.putString(USER_SESSION_KEY, key);
        editor.commit();
    }

    public boolean hasUserSessionKey() {
        return sharedPreferences.contains(USER_SESSION_KEY);
    }

    public String getUserSessionKey() {
        return sharedPreferences.getString(USER_SESSION_KEY, "");
    }

    public void deleteUserSessionKey() {
        editor.remove(USER_SESSION_KEY);
        editor.commit();
    }
}
