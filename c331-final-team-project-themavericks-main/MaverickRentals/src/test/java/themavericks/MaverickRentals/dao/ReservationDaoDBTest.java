package themavericks.MaverickRentals.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import themavericks.MaverickRentals.entity.Reservation;
import themavericks.MaverickRentals.exception.CustomException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationDaoDBTest {

    @Autowired
    ReservationDao reservationDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Before
//    public void clean() {
//        String sqlDELETE_Reservation = "DELETE FROM Reservation";
//        String sqlDELETE_Station = "DELETE FROM Station";
//        String sqlDELETE_BikeType = "DELETE FROM BikeType";
//        String sqlDELETE_Bike = "DELETE FROM Bike";
//
//        jdbcTemplate.update(sqlDELETE_Station);
//        jdbcTemplate.update(sqlDELETE_BikeType);
//        jdbcTemplate.update(sqlDELETE_Bike);
//        jdbcTemplate.update(sqlDELETE_Reservation);
//    }

    @Test
    public void addReservationTest() {
        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalDateTime.now());
        reservation.setStartStationId(1);
        reservation.setPrice(BigDecimal.valueOf(10.99));
        reservation.setCustomerName("John Doe");
        reservation.setBikeId(1);

        Reservation addedReservation = reservationDao.addReservation(reservation);

        assertNotNull(addedReservation.getReservationId());
        assertEquals(reservation.getStartTime(), addedReservation.getStartTime());
        assertEquals(reservation.getStartStationId(), addedReservation.getStartStationId());
        assertEquals(reservation.getPrice(), addedReservation.getPrice());
        assertEquals(reservation.getCustomerName(), addedReservation.getCustomerName());
        assertEquals(reservation.getBikeId(), addedReservation.getBikeId());
    }

    @Test
    public void updateReservation() throws CustomException {
        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalDateTime.now());
        reservation.setStartStationId(1);
        reservation.setPrice(BigDecimal.valueOf(10.99));
        reservation.setCustomerName("John Doe");
        reservation.setBikeId(1);
        Reservation addedReservation = reservationDao.addReservation(reservation);

        LocalDateTime endTime = LocalDateTime.now();
        int endStationId = 2;
        BigDecimal price = BigDecimal.valueOf(15.99);
        reservationDao.updateReservation(addedReservation.getReservationId(), endTime, endStationId, price);

        Reservation updatedReservation = reservationDao.getReservation(addedReservation.getReservationId());
        assertEquals(endTime.truncatedTo(ChronoUnit.SECONDS), updatedReservation.getEndTime());
        assertEquals(price, updatedReservation.getPrice());
    }

    @Test
    public void getReservation() {
        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalDateTime.now());
        reservation.setStartStationId(1);
        reservation.setPrice(BigDecimal.valueOf(10.99));
        reservation.setCustomerName("John Doe");
        reservation.setBikeId(1);
        Reservation addedReservation = reservationDao.addReservation(reservation);

        Reservation retrievedReservation = reservationDao.getReservation(addedReservation.getReservationId());
        assertNotNull(retrievedReservation);
        assertEquals(addedReservation.getReservationId(), retrievedReservation.getReservationId());
    }

    @Test
    public void getAllReservations() {
        List<Reservation> reservations = reservationDao.getAllReservations();
        assertNotNull(reservations);
        assertFalse(reservations.isEmpty());
    }
}