package gr.unipi.msc.android.movieexercise.ui.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.ui.BaseActivity;
import gr.unipi.msc.android.movieexercise.ui.fragment.DiscoverMoviesFragment;
import gr.unipi.msc.android.movieexercise.ui.fragment.NewMoviesFragment;
import gr.unipi.msc.android.movieexercise.ui.fragment.PopularMoviesFragment;
import gr.unipi.msc.android.movieexercise.ui.fragment.SearchMoviesFragment;
import gr.unipi.msc.android.movieexercise.ui.fragment.UserProfileFragment;
import gr.unipi.msc.android.movieexercise.util.BottomNavigationViewHelper;

public class NavigationActivity extends BaseActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            getToolbar().setVisibility(View.VISIBLE);
            addContentPaddingTop();
            switch (item.getItemId()) {
                case R.id.navigation_discover:
                    getToolbar().setTitle(R.string.title_discover);
                    updateView(DiscoverMoviesFragment.newInstance());
                    return true;
                case R.id.navigation_new:
                    getToolbar().setTitle(R.string.title_new);
                    updateView(NewMoviesFragment.newInstance());
                    return true;
                case R.id.navigation_popular:
                    getToolbar().setTitle(R.string.title_popular);
                    updateView(PopularMoviesFragment.newInstance());
                    return true;
                case R.id.navigation_search:
                    getToolbar().setTitle(R.string.title_search);
                    updateView(SearchMoviesFragment.newInstance());
                    return true;
                case R.id.navigation_profile:
                    updateView(UserProfileFragment.newInstance());
                    getToolbar().setVisibility(View.GONE);
                    removeContentPaddingTop();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_navigation;
    }

    @Override
    public Fragment getMainFragment() {
        return DiscoverMoviesFragment.newInstance();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}
