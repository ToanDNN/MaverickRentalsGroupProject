import React from 'react';
import { Card, Button } from 'react-bootstrap';

function BikeCard({ bike, handleBikeSelection }) {
  return (
    <Card>
      <Card.Body>
        <Card.Title>Bike ID: {bike.bikeId}</Card.Title>
        <Card.Text>
          Bike Type: {bike.bikeType?.typeName}
          <br />
          Bike Price: ${bike.bikeType?.bikePrice.toFixed(2)}
        </Card.Text>
        <Button variant="primary" onClick={() => handleBikeSelection(bike.bikeId)}>
          Select
        </Button>
      </Card.Body>
    </Card>
  );
}

export default BikeCard;
