package gr.unipi.msc.android.movieexercise;

import android.app.Application;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import gr.unipi.msc.android.movieexercise.util.SharedPreferencesController;


public class MovieExercise extends Application {

    public static MovieExercise instance;

    public static JobManager JOB_MANAGER;
    public static SharedPreferencesController LOCAL_DATA;


    public MovieExercise() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        JOB_MANAGER = getJobManager();
        configureDataLayer();
    }

    private void configureJobManager() {
        JOB_MANAGER = new JobManager(new Configuration.Builder(this)
                .minConsumerCount(1)
                .maxConsumerCount(3)
                .loadFactor(3)
                .build());
    }

    public synchronized JobManager getJobManager() {
        if (JOB_MANAGER == null) {
            configureJobManager();
        }
        return JOB_MANAGER;
    }

    private void configureDataLayer() {
        LOCAL_DATA = new SharedPreferencesController(this);
    }

    public static MovieExercise getInstance() {
        return instance;
    }
}
