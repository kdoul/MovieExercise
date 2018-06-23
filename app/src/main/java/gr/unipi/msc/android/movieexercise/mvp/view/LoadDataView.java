package gr.unipi.msc.android.movieexercise.mvp.view;

import gr.unipi.msc.android.movieexercise.mvp.BaseView;


public interface LoadDataView extends BaseView {

    void showLoading();
    void hideLoading();

    void showRetry(String msg);
    void hideRetry();

    void showEmpty(String msg);
    void hideEmpty();
}
