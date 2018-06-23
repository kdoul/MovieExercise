package gr.unipi.msc.android.movieexercise.datamanager.model;

import java.util.List;

public class MovieResults {
    private final int page;
    private final List<MovieListObject> results;
    private final int total_results;
    private final int total_pages;

    public int getPage() {
        return page;
    }

    public List<MovieListObject> getResults() {
        return results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public MovieResults(int page, List<MovieListObject> results, int total_results, int total_pages) {

        this.page = page;
        this.results = results;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }


}
