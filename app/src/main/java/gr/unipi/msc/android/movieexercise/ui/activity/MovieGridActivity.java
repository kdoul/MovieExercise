package gr.unipi.msc.android.movieexercise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.datamanager.MovieResultUtils;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.ui.BaseActivity;
import gr.unipi.msc.android.movieexercise.ui.fragment.DiscoverMoviesFragment;
import gr.unipi.msc.android.movieexercise.ui.fragment.MovieGridFragment;
import gr.unipi.msc.android.movieexercise.ui.fragment.UserWatchedFragment;

public class MovieGridActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        Intent intentToBeCalled = new Intent(context, MovieGridActivity.class);
        return intentToBeCalled;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().setTitle("Your watchlist");
    }

    @Override
    public int getLayoutResource() {
        return R.layout.base_toolbar_layout;
    }

    @Override
    public Fragment getMainFragment() {
        return UserWatchedFragment.newInstance();
    }
}

