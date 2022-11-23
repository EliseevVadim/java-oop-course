package models;

public class Bus {
    private long id;
    private String carNumber;
    private byte sitPlaces;
    private byte generalCapacity;
    private long travelCompanyId;

    public Bus(long id, String carNumber, byte sitPlaces, byte generalCapacity, long travelCompanyId) {
        this.id = id;
        this.carNumber = carNumber;
        this.sitPlaces = sitPlaces;
        this.generalCapacity = generalCapacity;
        this.travelCompanyId = travelCompanyId;
    }

    public Bus(String carNumber, byte sitPlaces, byte generalCapacity, long travelCompanyId) {
        this.carNumber = carNumber;
        this.sitPlaces = sitPlaces;
        this.generalCapacity = generalCapacity;
        this.travelCompanyId = travelCompanyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public byte getSitPlaces() {
        return sitPlaces;
    }

    public void setSitPlaces(byte sitPlaces) {
        this.sitPlaces = sitPlaces;
    }

    public byte getGeneralCapacity() {
        return generalCapacity;
    }

    public void setGeneralCapacity(byte generalCapacity) {
        this.generalCapacity = generalCapacity;
    }

    public long getTravelCompanyId() {
        return travelCompanyId;
    }

    public void setTravelCompanyId(long travelCompanyId) {
        this.travelCompanyId = travelCompanyId;
    }
}
