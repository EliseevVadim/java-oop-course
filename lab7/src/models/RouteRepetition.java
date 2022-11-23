package models;

public class RouteRepetition {
    private long id;
    private String repetitionName;

    public RouteRepetition(long id, String repetitionName) {
        this.id = id;
        this.repetitionName = repetitionName;
    }

    public RouteRepetition(String repetitionName) {
        this.repetitionName = repetitionName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRepetitionName() {
        return repetitionName;
    }

    public void setRepetitionName(String repetitionName) {
        this.repetitionName = repetitionName;
    }
}
