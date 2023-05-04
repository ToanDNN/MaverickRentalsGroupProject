import React, {useState, useEffect} from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { updateReservation } from '../api/apiCalls';
import StationSelect from './StationSelect';
import {getAllStations, getBikesByStation} from '../api/apiCalls';
import EndTimeInput from './EndTimeInput';

function CheckIn() {
  const location = useLocation();
  const reservation = location.state?.reservation; // Safetly access the reservation
  const navigate = useNavigate();

  const [endStationId, setEndStationId] = useState('');
  const [endTime, setEndTime] = useState('');
  const [message, setMessage] = useState(null);
  const [stations, setStations] = useState([])

  const handleStationSelect = (e) => {
    setEndStationId(e.target.value)
  }

  const handleEndTimeChange = (event) => {
    setEndTime(event.target.value);
  };

  useEffect(() => {
    if (!reservation) {
      // Redirect to another page if the reservation object is not available
      // Replace '/home' with the desired path
      navigate('/');
    }
  }, [navigate, reservation]);

  useEffect(() => {
    async function fetchData() {
      const data = await getAllStations();
      setStations(data);
    }
    fetchData();
  }, []);


  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await updateReservation(
        reservation.reservationId,
        endTime,
        endStationId,
        reservation.bikeId
      );
      setMessage(response);
      console.log(response);

      // Redirect to a new page if the reservation was successfully updated
      if (response === 'Reservation updated') {
        navigate('/confirmation', { state: { reservation } });
      }
    } catch (error) {
      setMessage(error.message);
    }
  };

  return (
    reservation && (
      <div className="container mt-5">
        <h1 className="mb-4">Check In</h1>
        
        <div className="row">
          <div className="col-sm-6">
            <p>
              <strong>Reservation Id:</strong> {reservation.reservationId}
            </p>
            <p>
              <strong>Start Time:</strong> {reservation.startTime}
            </p>
            <p>
              <strong>End Time:</strong> {reservation.endTime}
            </p>
            <p>
              <strong>Start Station ID:</strong> {reservation.startStationId}
            </p>
            <p>
              <strong>End Station ID:</strong> {reservation.endStationId}
            </p>
            <p>
              <strong>Price:</strong> ${reservation.price.toFixed(2)}
            </p>
            <p>
              <strong>Customer Name:</strong> {reservation.customerName}
            </p>
            <p>
              <strong>Bike ID:</strong> {reservation.bikeId}
            </p>
          </div>
          <div className="col-sm-6">
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label htmlFor="endStationId">End Station ID:</label>
                  <StationSelect stations={stations}handleStationSelect={handleStationSelect}/>
              </div>
              <EndTimeInput endTime={endTime} handleEndTimeChange={handleEndTimeChange} />
              <button type="submit" className="btn btn-primary">
                Update Reservation  
              </button>
            </form>
          </div>

        </div>
        {message && <p className="mt-3">{message}</p>}
      </div>
    )
  )
}

export default CheckIn