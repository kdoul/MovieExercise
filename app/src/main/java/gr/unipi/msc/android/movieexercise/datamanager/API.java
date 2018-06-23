package gr.unipi.msc.android.movieexercise.datamanager;

import gr.unipi.msc.android.movieexercise.datamanager.model.MovieDetails;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieImages;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieResults;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieState;
import gr.unipi.msc.android.movieexercise.datamanager.model.PostResponse;
import gr.unipi.msc.android.movieexercise.datamanager.model.UserAuthToken;
import gr.unipi.msc.android.movieexercise.datamanager.model.UserProfile;
import gr.unipi.msc.android.movieexercise.datamanager.model.UserSessionID;
import gr.unipi.msc.android.movieexercise.datamanager.model.UserRequestToken;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class API {
    static final String BASE_URL = "http://api.themoviedb.org/3/";

    public interface ROUTES {
        @GET("discover/movie")
        Call<MovieResults> discoverMovies(@Query("api_key") String apiKey, @Query("page") int page);

        @GET("discover/movie")
        Call<MovieResults> discoverMovies(@Query("api_key") String apiKey);

        @GET("movie/popular")
        Call<MovieResults> getPopularMovies(@Query("api_key") String apiKey);

        @GET("movie/popular")
        Call<MovieResults> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

        @GET("movie/upcoming")
        Call<MovieResults> getUpcomingMovies(@Query("api_key") String apiKey);

        @GET("movie/upcoming")
        Call<MovieResults> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") int page);

        @GET("search/movie")
        Call<MovieResults> search(@Query("api_key") String apiKey, @Query("query") String query);

        @GET("search/movie")
        Call<MovieResults> search(@Query("api_key") String apiKey, @Query("query") String query,  @Query("page") int page);

        @GET("discover/movie")
        Call<MovieResults> discoverMovies(@Query("api_key") String apiKey, @Query("page") int page, @Query("sort_by")String sortingType);

        //MOVIE DETAIL
        @GET("movie/{id}")
        Call<MovieDetails> movieDetails(@Path("id") int movieID,@Query("api_key") String apiKey);

        //MOVIE IMAGES
        @GET("movie/{id}/images")
        Call<MovieImages> movieImages(@Path("id") int movieID, @Query("api_key") String apiKey);

        @GET("authentication/token/new")
        Call<UserAuthToken> getUserAuthToken(@Query("api_key") String api_key);

        @GET("authentication/token/validate_with_login")
        Call<UserRequestToken> getUserSessionToken(@Query("api_key") String api_key, @Query("request_token") String request_token, @Query("username") String username, @Query("password") String password);

        @GET("account")
        Call<UserProfile> getUserProfile(@Query("api_key") String api_key, @Query("session_id") String session_token);

        @GET("account/0/watchlist/movies")
        Call<MovieResults> getUserWatchList(@Query("api_key") String api_key, @Query("session_id") String sesson_token);

        @GET("account/0/watchlist/movies")
        Call<MovieResults> getUserWatchList(@Query("api_key") String api_key, @Query("session_id") String sesson_token, @Query("page") int page);

        @GET("authentication/session/new")
        Call<UserSessionID> getUserSessionID(@Query("api_key") String api_key, @Query("request_token") String session_token);

        @GET("movie/{movie_id}/account_states")
        Call<MovieState> getMovieState(@Path("movie_id")int movie_id, @Query("api_key") String api_key, @Query("session_id") String session_token);

        @POST("account/0/watchlist")
        Call<PostResponse> postToUserWatchList(@Query("api_key") String api_key, @Query("session_id") String sesson_token, @Body RequestBody params);

    }

    public static ROUTES http() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ROUTES.class);
    }
}
