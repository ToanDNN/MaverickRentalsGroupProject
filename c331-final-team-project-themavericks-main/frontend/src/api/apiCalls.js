import axios from 'axios';

const baseUrl = 'http://localhost:8080';

export const getAllStations = async () => {
  try {
    const response = await axios.get(`${baseUrl}/stations`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const getBikesByStation = async (stationId) => {
    try {
      const response = await axios.get(`${baseUrl}/bikes/${stationId}`);
      return response.data;
    } catch (error) {
      console.error(error);
    }
};

export const getReservationById = async (reservationId) => {
  try {
    const response = await axios.get(`${baseUrl}/reservation/${reservationId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching reservation by id:', error);
    return null;
  }
};

// localhost:8080/reservation/1?endTime=2023-04-23T14:00:00&endStationId=2&bikeId=1
export async function updateReservation(reservationId, endTime, endStationId, bikeId) {
  try {
    const response = await axios.put(`${baseUrl}/reservation/${reservationId}`, null, {
      params: {
        endTime,
        endStationId,
        bikeId,
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(error.response.data);
    }
}

export async function createReservation(numOfHours, startStationId, customerName, bikeId) {
    try {
        const response = await axios.post(`${baseUrl}/createReservation`, null, {
            params: {
                numOfHours,
                startStationId,
                customerName,
                bikeId
            },
        });
        return response.data;
    } catch (error) {
        throw new Error(error.response.data);
    }
}