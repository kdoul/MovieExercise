package gr.unipi.msc.android.movieexercise.datamanager.connector;

import org.junit.Test;

import java.io.IOException;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieResults;
import gr.unipi.msc.android.movieexercise.util.C;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kdoul on 20/06/2018.
 */
public class MovieDiscoveryApiTest {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(C.TMDB_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Test
    public void discoverMovies() throws Exception {
        System.out.println("Test started.");
//        MovieDiscoveryApi moviesApi = retrofit.create(MovieDiscoveryApi.class);
//        moviesApi.discoverMovies(C.API_KEY).enqueue(new Callback<MovieResults>() {
//            @Override
//            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    MovieResults results = response.body();
//
//
//                }else {
//                    System.out.println("Error:");
//                    System.out.println(response.code());
//                    try {
//                        System.out.println(response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieResults> call, Throwable t) {
//                try {
//                    throw t;
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//                System.err.println("Failure to make request");
//            }
//        });
//        Thread.sleep(300000);
    }

    @Test
    public void discoverMovies1() throws Exception {
    }

}