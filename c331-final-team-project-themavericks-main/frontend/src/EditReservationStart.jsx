import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getReservationById } from './api/apiCalls';
import 'bootstrap/dist/css/bootstrap.min.css';

function EditReservationStart() {
  const [reservationId, setReservationId] = useState('');
  const [reservation, setReservation] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    setReservation(null);
    setErrorMessage('');

    if (reservationId) {
      const data = await getReservationById(reservationId);
      if (data) {
        setReservation(data);
      } else {
        setErrorMessage('No reservation exists with that ID');
      }
    }
  };

  const handleCheckIn = () => {
    navigate('/checkin', { state: { reservation } });
  };

  return (
    <div className="container">
      <h1 className="text-center mt-3">Reservation Details</h1>
      <form className="form-inline justify-content-center mt-3" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="reservationId" className="mr-3">
            Reservation ID:
          </label>
          
          <input
            type="number"
            id="reservationId"
            className="form-control mr-3"
            value={reservationId}
            onChange={(e) => setReservationId(e.target.value)}
            style={{ appearance: 'none' }}
          />

        </div>
        <button type="submit" className="btn btn-primary">
          Search
        </button>
      </form>
      {errorMessage && <p className="text-danger mt-3">{errorMessage}</p>}
      {reservation && (
        <div className="card mt-3">
          <div className="card-body">
            <h5 className="card-title">Reservation Information</h5>
            <p className="card-text">Start Time: {reservation.startTime}</p>
            <p className="card-text">End Time: {reservation.endTime}</p>
            <p className="card-text">Start Station ID: {reservation.startStationId}</p>
            <p className="card-text">End Station ID: {reservation.endStationId}</p>
            <p className="card-text">Price: ${reservation.price.toFixed(2)}</p>
            <p className="card-text">Customer Name: {reservation.customerName}</p>
            <p className="card-text">Bike ID: {reservation.bikeId}</p>
            <button className="btn btn-primary mt-3" onClick={handleCheckIn}>
              Check In
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default EditReservationStart;
