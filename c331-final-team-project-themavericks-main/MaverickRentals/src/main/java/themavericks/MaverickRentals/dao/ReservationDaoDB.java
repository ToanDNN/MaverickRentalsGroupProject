package themavericks.MaverickRentals.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import themavericks.MaverickRentals.entity.Reservation;
import themavericks.MaverickRentals.entity.Station;
import themavericks.MaverickRentals.exception.CustomException;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationDaoDB implements ReservationDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Reservation addReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservation(startTime, startStationId, price, customerName, bikeId) VALUES(?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(reservation.getStartTime()));
            preparedStatement.setInt(2, reservation.getStartStationId());
            preparedStatement.setBigDecimal(3, reservation.getPrice());
            preparedStatement.setString(4, reservation.getCustomerName());
            preparedStatement.setInt(5, reservation.getBikeId());


            return preparedStatement;
        }, keyHolder);
        reservation.setReservationId(keyHolder.getKey().intValue());
        return reservation;
    }

    @Override
    public void updateReservation(int reservationId, LocalDateTime endTime, int endStationId, BigDecimal price) throws CustomException {
        String sql = "UPDATE Reservation SET endTime = ?, endStationId = ?, price = ? WHERE reservationId = ?";
        int updatedRows = jdbcTemplate.update(sql, Timestamp.valueOf(endTime), endStationId, price, reservationId);
        if(updatedRows == 0) throw new CustomException("Error updating reservation");
    }

    @Override
    public Reservation getReservation(int reservationId) {
        String sql = "SELECT * FROM Reservation WHERE reservationId = ?";
        return jdbcTemplate.queryForObject(sql, new ReservationRowMapper(), reservationId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        String sql = "SELECT * FROM Reservation r "
                + "JOIN Station s ON r.startStationId = s.stationId  "
                + "JOIN Bike b ON r.bikeId = b.bikeId;";
        return jdbcTemplate.query(sql, new ReservationRowMapper());
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

    private static class getAllReservationRowMapper implements RowMapper<Reservation>{
        @Override
        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation reservation = new Reservation();
                    rs.getInt("resevationId");
                    rs.getTimestamp("startTime").toLocalDateTime();
                    rs.getTimestamp("endTime");
                    rs.getInt("startStationId");
                    rs.getObject("endStationId");
                    rs.getBigDecimal("price");
                    rs.getString("customerName");
                    rs.getInt("bikeId");
                    return reservation;
        }
    }
    private static class ReservationRowMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation reservation = new Reservation();
            reservation.setReservationId(rs.getInt("reservationId"));
            reservation.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
            reservation.setEndTime(rs.getTimestamp("endTime") != null ? rs.getTimestamp("endTime").toLocalDateTime() : null);
            reservation.setStartStationId(rs.getInt("startStationId"));
            reservation.setEndStationId(rs.getInt("endStationId"));
            reservation.setPrice(rs.getBigDecimal("price"));
            reservation.setCustomerName(rs.getString("customerName"));
            reservation.setBikeId(rs.getInt("bikeId"));
            return reservation;
        }
    }
}
