package models;

public class SoldTicket {
    private long id;
    private int ticketPrice;
    private byte sitNumber;
    private long flightId;
    private long sellerId;

    public SoldTicket(long id, int ticketPrice, byte sitNumber, long flightId, long sellerId) {
        this.id = id;
        this.ticketPrice = ticketPrice;
        this.sitNumber = sitNumber;
        this.flightId = flightId;
        this.sellerId = sellerId;
    }

    public SoldTicket(int ticketPrice, byte sitNumber, long flightId, long sellerId) {
        this.ticketPrice = ticketPrice;
        this.sitNumber = sitNumber;
        this.flightId = flightId;
        this.sellerId = sellerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public byte getSitNumber() {
        return sitNumber;
    }

    public void setSitNumber(byte sitNumber) {
        this.sitNumber = sitNumber;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }
}
