package themavericks.MaverickRentals.dao;

import themavericks.MaverickRentals.entity.BikeType;

import java.util.List;

public interface BikeTypeDao {

    public List<BikeType> getAllBikeTypes();

    public BikeType getBikeTypeById(int bikeTypeId);
}
