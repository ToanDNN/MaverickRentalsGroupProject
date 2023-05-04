package themavericks.MaverickRentals.dao;

import themavericks.MaverickRentals.entity.Bike;
import themavericks.MaverickRentals.exception.CustomException;

import java.math.BigDecimal;
import java.util.List;

public interface BikeDao {

    public List<Bike> findAvailableBikesByStationId(int stationId);

    public List<Bike> findAllAvailableBikes();

    public List<Bike> findAllBikes();

    void updateBikeAvailability(int bikeId, boolean available) throws CustomException;

    BigDecimal getPricePerHour(int bikeId);

    void updateBikeStatus(int bikeId, boolean available, int stationId) throws CustomException;
    
    //public void deleteBikeById(int bikeId);

}
