package gr.unipi.msc.android.movieexercise.ui.fragment;

import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.datamanager.model.UserProfile;
import gr.unipi.msc.android.movieexercise.mvp.presenter.UserProfilePresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.UserProfileView;
import gr.unipi.msc.android.movieexercise.ui.BaseFragment;
import gr.unipi.msc.android.movieexercise.ui.activity.MovieGridActivity;
import gr.unipi.msc.android.movieexercise.util.C;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserProfileFragment} interface
 * to handle interaction events.
 * Use the {@link UserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfileFragment extends BaseFragment implements UserProfileView{

    private View mainView, loadingView;

    private ImageView userAvatar;
    private TextView username, userID, name;
    private UserProfile profile;
    private Button logoutBtn, viewWatchlistBtn;
    private UserProfilePresenter presenter;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void instantiatePresenter() {
        presenter = new UserProfilePresenter(this);
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
        return R.layout.fragment_user_profile;
    }

    @Override
    public void mapGUI(View view) {
        mainView = view.findViewById(R.id.main_container);
        loadingView = view.findViewById(R.id.view_loading);

        username = mainView.findViewById(R.id.username);
        name = mainView.findViewById(R.id.name);
        userAvatar = mainView.findViewById(R.id.profile);
        logoutBtn = mainView.findViewById(R.id.button_logout);
        viewWatchlistBtn = mainView.findViewById(R.id.button_watchlist);
    }

    @Override
    public void configureGUI() {
        getActivity().setTitle("Profile");
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLogoutClicked();
            }
        });
        viewWatchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onGoToWatchedClicked();
            }
        });
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


    @Override
    public void updateUsername(String username) {
        this.username.setText(username);
        this.username.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateID(int id) {
//        this.userID.setText(String.valueOf(id));
//        this.userID.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateName(String name) {
        this.name.setText(name);
        this.name.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateGravatar(String hash) {
        Picasso.get().load("http://www.gravatar.com/avatar/" + hash + "?s=204&d=404").into(userAvatar, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public void goToLoginPage() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, UserLoginFragment.newInstance())
                .commit();
    }

    @Override
    public void goToUserWatched() {
        startActivity(MovieGridActivity.getCallingIntent(getActivity()));
    }
}
