package themavericks.MaverickRentals.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import themavericks.MaverickRentals.entity.Bike;
import themavericks.MaverickRentals.entity.BikeType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BikeTypeDaoDB implements BikeTypeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<BikeType> getAllBikeTypes() {
        final String sql = "SELECT * FROM BikeType";
        return jdbcTemplate.query(sql, new BikeTypeMapper());
    }

    @Override
    public BikeType getBikeTypeById(int bikeTypeId) {
        final String sql = "SELECT * FROM BikeType WHERE bikeTypeId = ?";
        return jdbcTemplate.queryForObject(sql, new BikeTypeMapper(), bikeTypeId);
    }

    private static final class BikeTypeMapper implements RowMapper<BikeType> {
        @Override
        public BikeType mapRow(ResultSet rs, int rowNum) throws SQLException {
            BikeType bikeType = new BikeType();
            bikeType.setBikeTypeId(rs.getInt("bikeTypeId"));
            bikeType.setTypeName(rs.getString("typeName"));
            bikeType.setBikePrice(rs.getBigDecimal("bikePrice"));
            return bikeType;
        }
    }


}
