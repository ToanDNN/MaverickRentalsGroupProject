import React from 'react';
import BikeCard from './BikeCard';
import { Card } from 'react-bootstrap';

function BikeList({ bikes, handleBikeSelection }) {
  return (
    <div>
      <h2>Bikes</h2>
      <Card>
        {bikes && bikes.length > 0 ? (
          bikes.map((bike) => (
            <BikeCard key={bike.bikeId} bike={bike} handleBikeSelection={handleBikeSelection} />
          ))
        ) : (
          <p>{bikes ? '' : 'No bikes at this station'}</p>
        )}
      </Card>
    </div>
  );
}

export default BikeList;
