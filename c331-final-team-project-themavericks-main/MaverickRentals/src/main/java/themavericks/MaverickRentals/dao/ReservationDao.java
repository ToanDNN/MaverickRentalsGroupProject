package themavericks.MaverickRentals.dao;

import themavericks.MaverickRentals.entity.Reservation;
import themavericks.MaverickRentals.exception.CustomException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationDao {

    Reservation addReservation(Reservation reservation);
    void updateReservation(int reservationId, LocalDateTime endTime, int endStationId, BigDecimal price) throws CustomException;
    Reservation getReservation(int reservationId);
    List<Reservation> getAllReservations();
}
