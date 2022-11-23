package models;

public class TravelCompany {
    private long id;
    private String companyName;
    private String companyINN;

    public TravelCompany(long id, String companyName, String companyINN) {
        this.id = id;
        this.companyName = companyName;
        this.companyINN = companyINN;
    }

    public TravelCompany(String companyName, String companyINN) {
        this.companyName = companyName;
        this.companyINN = companyINN;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyINN() {
        return companyINN;
    }

    public void setCompanyINN(String companyINN) {
        this.companyINN = companyINN;
    }
}
