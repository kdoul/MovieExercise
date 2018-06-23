package gr.unipi.msc.android.movieexercise.datamanager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;


public abstract class BaseAction extends Job {

    public static final int HIGH_PRIORITY = 1;
    public static final int RETRY_LIMIT = 0;
    public static final String NETWORK_ERROR = "Internet connection failure.";

    protected BaseCallback callback;
    protected String errorReason;

    public BaseAction(BaseCallback callback) {
        super(new Params(HIGH_PRIORITY));
        this.callback = callback;
    }

    @Override
    public void onAdded() {/*EMPTY FOR NOW*/}


    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        callback.onError(errorReason);
    }

    @Override
    protected int getRetryLimit() {
        return RETRY_LIMIT;
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        throwable.printStackTrace();
        errorReason = throwable.getMessage();
        onCancel(0, throwable);
        return new RetryConstraint(false);
    }

}