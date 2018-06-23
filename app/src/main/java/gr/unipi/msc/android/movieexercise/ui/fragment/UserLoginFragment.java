package gr.unipi.msc.android.movieexercise.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.mvp.presenter.UserLoginPresenter;
import gr.unipi.msc.android.movieexercise.mvp.view.UserLoginView;
import gr.unipi.msc.android.movieexercise.ui.BaseFragment;


public class UserLoginFragment extends BaseFragment implements UserLoginView{

    private Button loginbtn;
    private TextView websiteText;
    private EditText username, password;
    private UserLoginPresenter presenter;
    private View loadingView, mainView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Login");

    }

    public static UserLoginFragment newInstance() { return new UserLoginFragment(); }

    @Override
    public void instantiatePresenter() {
        presenter = new UserLoginPresenter(this);
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
        return R.layout.fragment_user_login;
    }

    @Override
    public void mapGUI(View view) {
        mainView = view.findViewById(R.id.main_container);
        loadingView = view.findViewById(R.id.view_loading);

        loginbtn = mainView.findViewById(R.id.btn_login);
        websiteText = mainView.findViewById(R.id.link_signup);
        username = mainView.findViewById(R.id.input_email);
        password = mainView.findViewById(R.id.input_password);
    }

    @Override
    public void configureGUI() {
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.performLogin(username.getText().toString(), password.getText().toString());
            }
        });
        websiteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCreateNewAccountClicked();
            }
        });

    }

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
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showRetry(String msg) {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showEmpty(String msg) {

    }

    @Override
    public void hideEmpty() {

    }

    @Override
    public void goToProfile() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, UserProfileFragment.newInstance())
                .commit();
    }

    @Override
    public void enableLoginBtn() {
        loginbtn.setActivated(true);
    }

    @Override
    public void disableLoginBtn() {
        loginbtn.setActivated(false);
    }

    @Override
    public void openTMDBSignUpWebsite() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse("https://www.themoviedb.org/account/signup"));
        startActivity(browserIntent);
    }
}
