import React from 'react';
import { Form, Row, Col } from 'react-bootstrap';

function EndTimeInput({ endTime, handleEndTimeChange }) {
  return (
    <div className="form-group">
      <label htmlFor="endTime">End Time:</label>
      <input
        type="datetime-local"
        className="form-control"
        id="endTime"
        value={endTime}
        onChange={handleEndTimeChange}
        required
      />
    </div>
  );
}

export default EndTimeInput;
