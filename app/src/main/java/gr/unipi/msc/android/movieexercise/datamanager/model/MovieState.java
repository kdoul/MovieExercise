package gr.unipi.msc.android.movieexercise.datamanager.model;


public class MovieState {
    private final int id;

    public int getId() {
        return id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public MovieState(int id, boolean favorite, boolean watchlist) {

        this.id = id;
        this.favorite = favorite;
        this.watchlist = watchlist;
    }

    private final boolean favorite;
    private final boolean watchlist;
}
