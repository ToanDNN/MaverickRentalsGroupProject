package themavericks.MaverickRentals.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import themavericks.MaverickRentals.entity.Bike;
import themavericks.MaverickRentals.entity.BikeType;
import themavericks.MaverickRentals.exception.CustomException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BikeDaoDB implements BikeDao{

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Bike> findAvailableBikesByStationId(int stationId) {
        String sql = "SELECT * FROM Bike " +
                "JOIN BikeType USING (bikeTypeId) " +
                "WHERE stationId = ? AND available = true;";
        return jdbcTemplate.query(sql, new Object[]{stationId}, new BikeMapperWithJoin());
    }

    @Override
    public List<Bike> findAllAvailableBikes() {
        String sql = "SELECT * FROM Bike JOIN BikeType USING (bikeTypeId) WHERE available = true;";
        return jdbcTemplate.query(sql, new BikeRowMapper());
    }
    
    @Override
    public List<Bike> findAllBikes() {
        String sql = "SELECT * FROM Bike JOIN BikeType USING (bikeTypeId);";
        return jdbcTemplate.query(sql, new BikeRowMapper());
    }

//    @Override
//    public void deleteBikeById(int bikeId){
//        String sql = "DELETE * FROM Bike "
//                + "WHERE bikeId = ?;";
//        jdbcTemplate.update(sql, bikeId);
//    }

    @Override
    public void updateBikeAvailability(int bikeId, boolean available) throws CustomException {
        String sql = "UPDATE Bike SET available = ? WHERE bikeId = ?";
        int updatedRows = jdbcTemplate.update(sql, available, bikeId);
        if(updatedRows == 0){
            throw new CustomException("Bike not found");
        }
    }

    @Override
    public void updateBikeStatus(int bikeId, boolean available, int stationId) throws CustomException {
        String sql = "UPDATE Bike SET available = ?, stationId = ? WHERE bikeId = ?";
        int updatedRows = jdbcTemplate.update(sql, available, stationId, bikeId);
        if (updatedRows == 0) {
            throw new CustomException("Bike not found");
        }
    }

    @Override
    public BigDecimal getPricePerHour(int bikeId) {
        String sql = "SELECT bt.bikePrice FROM Bike b JOIN BikeType bt ON b.bikeTypeId = bt.bikeTypeId WHERE b.bikeId = ?";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, bikeId);
    }

    public static final class BikeMapperWithJoin implements RowMapper<Bike>{
        @Override
        public Bike mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bike bike = new Bike();
            bike.setBikeId(rs.getInt("bikeId"));
            bike.setBikeTypeId(rs.getInt("bikeTypeId"));
            bike.setAvailable(rs.getBoolean("available"));
            bike.setStationId(rs.getInt("stationId"));

            BikeType bikeType = new BikeType();
            bikeType.setBikeTypeId(rs.getInt("bikeTypeId"));
            bikeType.setTypeName(rs.getString("typeName"));
            bikeType.setBikePrice(rs.getBigDecimal("bikePrice"));

            bike.setBikeType(bikeType);

            return bike;
        }
    }

    public class BikeRowMapper implements RowMapper<Bike> {

        @Override
        public Bike mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bike bike = new Bike();
            bike.setBikeId(rs.getInt("bikeId"));
            bike.setBikeTypeId(rs.getInt("bikeTypeId"));
            bike.setAvailable(rs.getBoolean("available"));
            bike.setStationId(rs.getInt("stationId"));

            BikeType bikeType = new BikeType();
            bikeType.setBikeTypeId(rs.getInt("bikeTypeId"));
            bikeType.setTypeName(rs.getString("typeName"));
            bikeType.setBikePrice(rs.getBigDecimal("bikePrice"));

            bike.setBikeType(bikeType);

            return bike;
        }
    }




}
