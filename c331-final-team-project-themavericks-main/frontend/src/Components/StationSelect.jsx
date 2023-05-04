import React from 'react';
import { Form, Row, Col } from 'react-bootstrap';

function StationSelect({ stations, handleStationSelect }) {
  return (
    <Form.Select onChange={handleStationSelect}>
      <option value="">Select a station</option>
       {stations && stations.map((station) => (
          <option key={station.stationId} value={station.stationId}>
              Station id: {station.stationId} Station Name: {station.stationName}
          </option>
          ))}
    </Form.Select>
  );
}

export default StationSelect;
