import React from 'react';
import Carousel from 'react-material-ui-carousel';
import { Card, CardMedia, CardContent, Typography, Button } from '@mui/material';


export function CarouselComponent(props) {
    return (
        <Carousel>
            {
                props.bikes && props.bikes.length > 0 ? (
                    props.bikes.slice(0, 10).map((bike) => (
                    <BikeSlide key={bike.bikeId} bike={bike} bikeImages={props.bikeImages} onClick={props.onClick} />
                    ))
                ) : (
            <p>{props.bikes ? '' : 'No bikes at this station'}</p>
            )}
        
        </Carousel>
    )
};

export function BikeSlide(props) {
    const { bike, bikeImages, onClick } = props;
    const id = bike.bikeId;
    const bikeType = bike.bikeType;

    const type = bikeType.typeName;

    const image = bikeImages[type] ? bikeImages[type].image : null;


    const handleSelect = () => {
        onClick(id, bikeType.bikePrice);
    };
    return (
        <Card sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', height: '100%', borderRadius: 'borderRadius' }}>
            <Typography variant="h2" align="center">
                Pick your favorite Bike!
            </Typography>
            <CardMedia component="img" sx={{ width: '70%', height: '500px' }} image={image} title={type} />
            <CardContent sx={{ flexGrow: 1, display: 'flex', flexDirection: 'column', justifyContent: 'space-between', alignItems: 'center' }}>
                <Typography variant="h6" align="center">
                    {type}
                </Typography>
                <Typography variant="h6" align="center" gutterBottom>
                    {`$${bikeType.bikePrice.toFixed(2)} per hour`}
                </Typography>
                <Button variant="contained" onClick={handleSelect}>
                    Select
                </Button>
            </CardContent>
        </Card>
    );

}
