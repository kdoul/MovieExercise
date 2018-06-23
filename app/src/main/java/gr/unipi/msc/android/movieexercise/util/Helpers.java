package gr.unipi.msc.android.movieexercise.util;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import gr.unipi.msc.android.movieexercise.MovieExercise;
import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.ui.BaseActivity;


public class Helpers {

    public static int getAlphaColor(int color, float scrollRatio) {
        return Color.argb((int) (scrollRatio * 255f), Color.red(color), Color.green(color), Color.blue(color));
    }

    public static void restoreToolbarColor(BaseActivity activity, Toolbar toolbar) {
        if(toolbar != null && activity != null) {
            ColorDrawable toolbarBackground = (ColorDrawable) toolbar.getBackground();
            toolbarBackground.setColor(activity.getResources().getColor(R.color.colorPrimary));
            activity.getSupportActionBar().setBackgroundDrawable(toolbarBackground);
        }
    }

    public static void setContentBehindToolbar(BaseActivity activity) {
        activity.removeContentPaddingTop();
    }

    public static void hideSystemUI(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
