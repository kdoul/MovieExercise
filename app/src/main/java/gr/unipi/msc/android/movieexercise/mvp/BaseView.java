package gr.unipi.msc.android.movieexercise.mvp;

public interface BaseView {

    void showView();
    void hideView();

    void showFeedback(String msg);
    void destroyItself();
}
