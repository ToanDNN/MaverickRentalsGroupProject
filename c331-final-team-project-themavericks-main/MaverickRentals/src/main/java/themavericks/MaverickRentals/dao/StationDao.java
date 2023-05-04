package themavericks.MaverickRentals.dao;

import themavericks.MaverickRentals.entity.Station;

import java.util.List;

public interface StationDao {

    List<Station> getAllStations();
    List<Station> findStationById(int stationId);
    boolean updateStation(int stationId, boolean isLeaving);

    public List<Station> findAllStations();
}
