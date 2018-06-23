package gr.unipi.msc.android.movieexercise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.datamanager.MovieResultUtils;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.ui.BaseActivity;
import gr.unipi.msc.android.movieexercise.ui.fragment.MovieDetailFragment;

public class MovieDetailActivity  extends BaseActivity {

    private MovieListObject movieListObject;
    public static final String TAG = MovieDetailFragment.class.getSimpleName();
    public static final String MOVIE_DETAIL = TAG + ".MOVIE_DETAIL";

    public static Intent getCallingIntent(Context context, MovieListObject movieListObject) {
        Intent intentToBeCalled = new Intent(context, MovieDetailActivity.class);
        intentToBeCalled.putExtra(MOVIE_DETAIL, new MovieResultUtils().serializeModel(movieListObject));

        return intentToBeCalled;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle args = getIntent().getExtras();

        if(args != null) {
            movieListObject = new MovieResultUtils().deserializeModel(args.getString(MOVIE_DETAIL));
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.base_toolbar_layout;
    }

    @Override
    public Fragment getMainFragment() {
        return MovieDetailFragment.newInstance(movieListObject);
    }
}
