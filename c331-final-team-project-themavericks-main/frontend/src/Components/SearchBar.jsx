import React from 'react';
import { Form } from 'react-bootstrap';

function SearchBar({ searchTerm, handleInputChange }) {
  return (
    <Form.Control
      type="text"
      placeholder="Type in the station name"
      value={searchTerm}
      onChange={handleInputChange}
    />
  );
}

export default SearchBar;
