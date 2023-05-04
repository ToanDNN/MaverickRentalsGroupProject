package themavericks.MaverickRentals.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import themavericks.MaverickRentals.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Profile("database")
public class StationDaoDB  implements StationDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Station> getAllStations() {
        String sql = "SELECT * FROM Station";
        return jdbcTemplate.query(sql, new StationMapper());
    }

    @Override
    public List<Station> findStationById(int stationId) {
        String sql = "SELECT * FROM Station WHERE stationId = ?";
        List<Station> stations = jdbcTemplate.query(sql, new StationMapper(), stationId);
        return stations;
    }

    @Override
    public boolean updateStation(int stationId, boolean isLeaving) {
        List<Station> stations = findStationById(stationId);
        String sql = "UPDATE Station SET stationAvailableBikes = ? WHERE stationId = ?";
        int bikesAvailable = stations.get(0).getStationAvailableBikes();
        try {
            if(isLeaving) {
                bikesAvailable--;
                return  jdbcTemplate.update(sql, bikesAvailable, stationId) > 0;
            } else {
                bikesAvailable++;
                return  jdbcTemplate.update(sql, bikesAvailable, stationId) > 0;
            }
        } catch (DataAccessException e) {
            return false;
        }

    }

    public List<Station> findAllStations() {
        String query = "SELECT * FROM Station";
        return jdbcTemplate.query(query, (rs, rowNum) ->
                new Station(
                        rs.getInt("stationId"),
                        rs.getString("stationName"),
                        rs.getInt("stationCapacity"),
                        rs.getInt("stationAvailableBikes")
                )
        );
    }

    public static final class StationMapper implements RowMapper<Station> {

        @Override
        public Station mapRow(ResultSet rs, int index) throws SQLException {
            Station station = new Station();
            station.setStationId(rs.getInt("stationId"));
            station.setStationName(rs.getString("stationName"));
            station.setStationCapacity(rs.getInt("stationCapacity"));
            station.setStationAvailableBikes(rs.getInt("stationAvailableBikes"));

            return station;
        }
    }
}