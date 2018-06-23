package gr.unipi.msc.android.movieexercise.util;

public enum SortingTypes {
    PUPULARITY_ASC("popularity.asc"),
    POPULARITY_DESC("popularity.desc"),
    RELEASE_ASC("release_date.asc"),
    RELEASE_DESC("release_date.desc"),
    REVENUE_ASC("revenue.asc"),
    REVENUE_DESC("revenue.desc"),
    VOTE_ASC("vote_average.asc"),
    VODE_DESC("vote_average.desc");

    private final String type;

    public String getType() {
        return type;
    }

    SortingTypes(String type) {
        this.type = type;

    }
}
