package gr.unipi.msc.android.movieexercise.datamanager.model;

import android.support.annotation.Nullable;

import java.util.List;


public class MovieDetails {

    @Nullable
    private String backdrop_path;
    private int budget;

    @Nullable
    private String homepage;
    private int id;
    private String original_title;
    private float popularity;
    private List<ProductionCompany> production_companies;

    @Nullable
    private String overview;

    @Nullable
    private String poster_path;
    private String release_date;

    @Nullable
    private int runtime;
    private int revenue;
    private String status;

    @Nullable
    private String tagline;

    private int vote_count;
    private float vote_average;

    @Nullable
    public String getBackdrop_path() {
        return backdrop_path;
    }

    public int getBudget() {
        return budget;
    }

    @Nullable
    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public float getPopularity() {
        return popularity;
    }

    @Nullable
    public String getOverview() {
        return overview;
    }

    @Nullable
    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    @Nullable
    public int getRuntime() {
        return runtime;
    }

    public int getRevenue() {
        return revenue;
    }

    public String getStatus() {
        return status;
    }

    @Nullable
    public String getTagline() {
        return tagline;
    }

    public int getVote_count() {
        return vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public List<ProductionCompany> getProduction_companies() {
        return production_companies;
    }

    public MovieDetails(String backdrop_path, int budget, String homepage, int id, String original_title, float popularity, List<ProductionCompany> production_companies, String overview, String poster_path, String release_date, int runtime, int revenue, String status, String tagline, int vote_count, float vote_average) {
        this.backdrop_path = backdrop_path;
        this.budget = budget;
        this.homepage = homepage;
        this.id = id;
        this.original_title = original_title;
        this.popularity = popularity;
        this.production_companies = production_companies;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.runtime = runtime;
        this.revenue = revenue;
        this.status = status;
        this.tagline = tagline;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
    }
}
