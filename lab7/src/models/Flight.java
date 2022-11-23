package models;

public class Flight {
    private long id;
    private String departureTime;
    private String arrivalTime;
    private byte availableTickets;
    private int ticketPrice;
    private long routeId;
    private long busId;

    public Flight(long id, String departureTime, String arrivalTime, byte availableTickets, int ticketPrice, long routeId, long busId) {
        this.id = id;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableTickets = availableTickets;
        this.ticketPrice = ticketPrice;
        this.routeId = routeId;
        this.busId = busId;
    }

    public Flight(String departureTime, String arrivalTime, byte availableTickets, int ticketPrice, long routeId, long busId) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableTickets = availableTickets;
        this.ticketPrice = ticketPrice;
        this.routeId = routeId;
        this.busId = busId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public byte getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(byte availableTickets) {
        this.availableTickets = availableTickets;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public long getBusId() {
        return busId;
    }

    public void setBusId(long busId) {
        this.busId = busId;
    }
}
