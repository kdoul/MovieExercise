package gr.unipi.msc.android.movieexercise.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.mvp.presenter.PopularMoviesPresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.PopularMoviesView;
import gr.unipi.msc.android.movieexercise.ui.BaseFragment;
import gr.unipi.msc.android.movieexercise.ui.adapter.MoviesAdapter;
import gr.unipi.msc.android.movieexercise.util.EndlessRecyclerViewScrollListener;

public class PopularMoviesFragment extends BaseFragment implements PopularMoviesView {

    public static final String TAG = MovieDetailFragment.class.getSimpleName();
    public static final String MOVIE_GRID = TAG + ".MOVIE_GRID";

    private RecyclerView recyclerView;
    private View loadingView, retryView, emptyView;
    private TextView retryMsg, emptyMsg;
    private Button retryButton;

    private MoviesAdapter adapter;
    private PopularMoviesPresenter presenter;

    private List<MovieListObject> movies;

    public static PopularMoviesFragment newInstance() { // LIST<MOVIELISTOBJECT>

        PopularMoviesFragment fragment = new PopularMoviesFragment();

//        Bundle args = new Bundle();
//        args.putStringArrayList(MOVIE_GRID, (ArrayList<String>) new MovieResultUtils().serializeModels(movies));
//
//        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
//
//        Bundle args = getArguments();
//
//        if(args != null) {
//            movies = new MovieResultUtils().deserializeModels(args.getStringArrayList(MOVIE_GRID));
//        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void instantiatePresenter() {
        presenter = new PopularMoviesPresenter(this, movies);
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
        return R.layout.fragment_recycler;
    }

    @Override
    public void mapGUI(View view) {
        recyclerView = view.findViewById(R.id.recycler);

        loadingView = view.findViewById(R.id.view_loading);
        emptyView = view.findViewById(R.id.view_empty);
        retryView = view.findViewById(R.id.view_retry);

        emptyMsg = emptyView.findViewById(R.id.text);
        retryMsg = retryView.findViewById(R.id.text);

        retryButton = retryView.findViewById(R.id.button);
    }

    @Override
    public void configureGUI() {

        //RECYCLER VIEW CONFIGURATIONS
        adapter = new MoviesAdapter(getActivity());
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(glm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadMoreData(page);
                System.out.println("EVENT ACTIVATED");
            }
        };
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(scrollListener);

        //RECYCLER VIEW CONFIGURATIONS


        //RETRY BUTTON CONFIGURATIONS
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createView();
            }
        });
        //RETRY BUTTON CONFIGURATIONS

    }

    @Override
    public void renderMovies(List<MovieListObject> movies) {
        adapter.setData(movies);
    }

    @Override
    public void addMovies(List<MovieListObject> movies) {
        adapter.addData(movies);
    }

    @Override
    public void clearMovies() {
        adapter.clearData();
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
    public void showRetry(String msg) {
        retryMsg.setText(msg);
        retryView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        retryView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty(String msg) {
        emptyMsg.setText(msg);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showView() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideView() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showFeedback(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void destroyItself() {
        getActivity().finish();
    }
}

