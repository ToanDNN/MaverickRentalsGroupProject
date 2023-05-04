import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';
import { getReservationById } from '../api/apiCalls'

function Confirmation() {
  const location = useLocation();
  
  const reservationId = location.state.reservation;

  const [reservation, setReservation] = useState(null);
  const [loading, setLoading] = useState(true);
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    const fetchReservation = async () => {
      try {
        const data = await getReservationById(reservationId.reservationId);
        console.log(reservationId.reservationId)
        if (data) {
          setReservation(data);
        } else {
          setErrorMessage('No reservation exists with that ID');
        }
      } catch (error) {
        setErrorMessage('Error fetching reservation data');
      } finally {
        setLoading(false);
      }
    };

    fetchReservation();
  }, [reservationId]);

  if (loading) {
    return <p>Loading...</p>;
  }

  if (errorMessage) {
    return <p>{errorMessage}</p>;
  }

  return (
    <Container>
      <Row className="justify-content-center">
        <Col md={8} className="text-center">
          <h1>Reservation Updated</h1>
          <p>Reservation Id: {reservation.reservationId}</p>
          <p>Start Time: {reservation.startTime}</p>
          <p>End Time: {reservation.endTime}</p>
          <p>Start Station ID: {reservation.startStationId}</p>
          <p>End Station ID: {reservation.endStationId}</p>
          <p>Price: ${reservation.price.toFixed(2)}</p>
          <p>Customer Name: {reservation.customerName}</p>
          <p>Bike ID: {reservation.bikeId}</p>
        </Col>
      </Row>
    </Container>
  );
}

export default Confirmation;
