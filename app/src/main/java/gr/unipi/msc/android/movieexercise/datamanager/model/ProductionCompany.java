package gr.unipi.msc.android.movieexercise.datamanager.model;

public class ProductionCompany {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
