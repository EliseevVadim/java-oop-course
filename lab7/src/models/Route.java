package models;

public class Route {
    private long id;
    private long departureCityId;
    private long arrivalCityId;
    private long repetitionId;
    private int averageTravelTime;
    private float travelDistance;

    public Route(long id, long departureCityId, long arrivalCityId, long repetitionId, int averageTravelTime, float travelDistance) {
        this.id = id;
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
        this.repetitionId = repetitionId;
        this.averageTravelTime = averageTravelTime;
        this.travelDistance = travelDistance;
    }

    public Route(long departureCityId, long arrivalCityId, long repetitionId, int averageTravelTime, float travelDistance) {
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
        this.repetitionId = repetitionId;
        this.averageTravelTime = averageTravelTime;
        this.travelDistance = travelDistance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(long departureCityId) {
        this.departureCityId = departureCityId;
    }

    public long getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(long arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public long getRepetitionId() {
        return repetitionId;
    }

    public void setRepetitionId(long repetitionId) {
        this.repetitionId = repetitionId;
    }

    public int getAverageTravelTime() {
        return averageTravelTime;
    }

    public void setAverageTravelTime(int averageTravelTime) {
        this.averageTravelTime = averageTravelTime;
    }

    public float getTravelDistance() {
        return travelDistance;
    }

    public void setTravelDistance(float travelDistance) {
        this.travelDistance = travelDistance;
    }
}
