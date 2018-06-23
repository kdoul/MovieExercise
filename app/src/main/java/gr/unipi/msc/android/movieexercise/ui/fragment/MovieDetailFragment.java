package gr.unipi.msc.android.movieexercise.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.datamanager.MovieResultUtils;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.mvp.presenter.MovieDetailPresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.MovieDetailView;
import gr.unipi.msc.android.movieexercise.ui.BaseActivity;
import gr.unipi.msc.android.movieexercise.ui.BaseFragment;
import gr.unipi.msc.android.movieexercise.util.C;
import gr.unipi.msc.android.movieexercise.util.Helpers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieDetailFragment} interface
 * to handle interaction events.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends BaseFragment implements MovieDetailView {

    public static final String TAG = MovieDetailFragment.class.getSimpleName();
    public static final String MOVIE_DETAIL = TAG + ".MOVIE_DETAIL";

    private View mainView, loadingView, taglineContainer, overviewContainer, blackMask;

    private FloatingActionButton watchedButton;

    private ImageView coverImage;
    private TextView title, year, homepage, companies, tagline, overview;

    private MovieListObject movieListObject;

    private MovieDetailPresenter presenter;

    public static MovieDetailFragment newInstance(MovieListObject movieListObject) {
        MovieDetailFragment fragment = new MovieDetailFragment();

        Bundle args = new Bundle();
        args.putString(MOVIE_DETAIL, new MovieResultUtils().serializeModel(movieListObject));

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Bundle args = getArguments();

        if (args != null) {
            movieListObject = new MovieResultUtils().deserializeModel(args.getString(MOVIE_DETAIL));
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void instantiatePresenter() {
        presenter = new MovieDetailPresenter(this, movieListObject);
    }

    @Override
    public void initializePresenter() {
        presenter.createView();
    }

    @Override
    public void finalizePresenter() {
        presenter.destroyView();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    public void mapGUI(View view) {
        mainView = view.findViewById(R.id.main_container);
        loadingView = view.findViewById(R.id.view_loading);

        title = mainView.findViewById(R.id.title);
        year =  mainView.findViewById(R.id.year);
        coverImage = view.findViewById(R.id.cover);
        blackMask = view.findViewById(R.id.black_mask);
        homepage =  mainView.findViewById(R.id.homepage);
        companies = mainView.findViewById(R.id.companies);
        taglineContainer = mainView.findViewById(R.id.tagline_container);
        tagline = mainView.findViewById(R.id.tagline);
        overviewContainer = mainView.findViewById(R.id.overview_container);
        overview =  mainView.findViewById(R.id.overview);
        watchedButton = view.findViewById(R.id.fab);
    }

    @Override
    public void configureGUI() {
        getActivity().setTitle(movieListObject.getTitle());
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onHomepageClicked();
            }
        });
        watchedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onWatchedClick();
            }
        });

        Helpers.setContentBehindToolbar((BaseActivity) getActivity());
        configureScrollFade();
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.onMainViewScrolled();
    }

    @Override
    public void updateBackground(String value) {
        Picasso.get().load(C.TMDB_LARGE_IMAGE_URI+value).into(coverImage, new Callback() {
            @Override
            public void onSuccess() {
                blackMask.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }

        });
    }

    @Override
    public void updateTitle(String value) {
        title.setText(value);
    }

    @Override
    public void updateYearOfRelease(String value) {
        year.setText(value);
    }

    @Override
    public void hideYearOfRelease() {
        year.setVisibility(View.GONE);
    }

    @Override
    public void updateHomepage(String value) {
        homepage.setText(value);
        homepage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideHomepage() {
        homepage.setVisibility(View.GONE);
    }

    @Override
    public void updateCompanies(String value) {
        companies.setText(value);
        companies.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCompanies() {
        companies.setVisibility(View.GONE);
    }

    @Override
    public void updateTagline(String value) {
        tagline.setText(value);
        taglineContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTagline() {
        taglineContainer.setVisibility(View.GONE);
    }

    @Override
    public void updateOverview(String value) {
        overview.setText(value);
        overviewContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOverview() {
        overviewContainer.setVisibility(View.GONE);
    }

    @Override
    public void openMovieWebsite(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(url));
        startActivity(browserIntent);
    }

//    @Override
//    public void openGallery() {
//        startActivity(MovieImagesActivity.getCallingIntent(getActivity(), movieModel.getId()));
//    }

    @Override
    public void updateToolbarColor() {

        Toolbar toolbar = ((BaseActivity)getActivity()).getToolbar();

        int scrollY = mainView.getScrollY();
        ColorDrawable background = (ColorDrawable) toolbar.getBackground();
        int padding = mainView.getPaddingTop();
        double alpha = (1 - (((double) padding - (double) scrollY) / (double) padding)) * 255.0;
        alpha = alpha < 0 ? 0 : alpha;
        alpha = alpha > 255 ? 255 : alpha;

        background.setAlpha((int) alpha);

        float scrollRatio = (float) (alpha / 255f);
        int titleColor = Helpers.getAlphaColor(Color.WHITE, scrollRatio);
        toolbar.setTitleTextColor(titleColor);
    }

    @Override
    public void setFABminus() {
        watchedButton.setImageResource(R.drawable.ic_remove_black_24dp);
    }

    @Override
    public void setFABplus() {
        watchedButton.setImageResource(R.drawable.ic_add);
    }

    @Override
    public void showLoginPrompt() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("You need to log-in before you can add movies to your watched list.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showRetry(String msg) {}

    @Override
    public void hideRetry() {}

    @Override
    public void showEmpty(String msg) {}

    @Override
    public void hideEmpty() {}

    @Override
    public void showView() {
        mainView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideView() {
        mainView.setVisibility(View.GONE);
    }

    @Override
    public void showFeedback(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void destroyItself() {
        getActivity().finish();
    }

    private void configureScrollFade() {

        ((BaseActivity)getActivity()).setShouldFadeToolbar(true);

        presenter.onMainViewScrolled();

        mainView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                presenter.onMainViewScrolled();
            }
        });
    }

}