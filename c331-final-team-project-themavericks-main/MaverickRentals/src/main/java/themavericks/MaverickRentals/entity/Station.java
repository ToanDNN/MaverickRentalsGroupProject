package themavericks.MaverickRentals.entity;

public class Station {
    private int stationId;
    private String stationName;
    private int stationCapacity;
    private int stationAvailableBikes;

    public Station() {
    }


    public Station(int stationId, String stationName, int stationCapacity, int stationAvailableBikes) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.stationCapacity = stationCapacity;
        this.stationAvailableBikes = stationAvailableBikes;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getStationCapacity() {
        return stationCapacity;
    }

    public void setStationCapacity(int stationCapacity) {
        this.stationCapacity = stationCapacity;
    }

    public int getStationAvailableBikes() {
        return stationAvailableBikes;
    }

    public void setStationAvailableBikes(int stationAvailableBikes) {
        this.stationAvailableBikes = stationAvailableBikes;
    }
}
