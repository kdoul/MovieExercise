package gr.unipi.msc.android.movieexercise.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.util.Helpers;


public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private View container;
    private int previousPadding=0;

    private boolean shouldFade = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        container = findViewById(R.id.container);

        configureToolbar();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, getMainFragment())
                .commit();
    }

    public void updateView(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!shouldFade) {
            Helpers.restoreToolbarColor(this, toolbar);
        }
    }

    private void configureToolbar() {
        toolbar = findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public abstract int getLayoutResource();
    public abstract Fragment getMainFragment();

    public Toolbar getToolbar() {
        return toolbar;
    }
    public void setShouldFadeToolbar(boolean shouldFade) {
        this.shouldFade = shouldFade;
    }
    public void removeContentPaddingTop() {

        if(container != null) {
            previousPadding = container.getPaddingTop();
            container.setPaddingRelative(0,0,0,0);
        }
    }

    public void addContentPaddingTop() {
        if(container != null) {
            container.setPaddingRelative(0,toolbar.getHeight(),0,0);
        }
    }
}
