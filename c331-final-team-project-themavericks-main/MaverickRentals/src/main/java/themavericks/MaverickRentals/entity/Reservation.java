package themavericks.MaverickRentals.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int startStationId;
    private Integer endStationId;
    private BigDecimal price;
    private String customerName;
    private int bikeId;

    public Reservation() {
    }

    public Reservation(LocalDateTime startTime, int startStationId, int bikeId) {
        this.startTime = startTime;
        this.startStationId = startStationId;
        //this.endStationId = endStationId;
        this.bikeId = bikeId;
    }

    public Reservation(int reservationId, LocalDateTime startTime, LocalDateTime endTime, int startStationId, Integer endStationId, BigDecimal price, String customerName, int bikeId) {
        this.reservationId = reservationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startStationId = startStationId;
        this.endStationId = endStationId;
        this.price = price;
        this.customerName = customerName;
        this.bikeId = bikeId;
    }


    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(int startStationId) {
        this.startStationId = startStationId;
    }

    public Integer getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(Integer endStationId) {
        this.endStationId = endStationId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }
}

