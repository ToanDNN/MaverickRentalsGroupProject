import React, { useState, useEffect } from 'react';
import { CarouselComponent } from './CarouselComponent';
import { CheckOut } from './Review';
import Reservation from './Reservation';
import { ReservationForm } from './Input';
import Typography from '@mui/material/Typography';
import { createReservation, getBikesByStation } from '../api/apiCalls';
import { useLocation } from 'react-router-dom';


// stationId will be passed in as a props
function CreateReservation() {
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const stationId = searchParams.get('stationId');

    const [bikes, setBikes] = useState(null);
    const [loading, setLoading] = useState(true);
    const [selectedBikeId, setSelectedBikeId] = useState(null);
    const [selectedBikePrice, setSelectedBikePrice] = useState(0);
    const [customerName, setCustomerName] = useState('');
    const [numOfHours, setNumOfHours] = useState('');
    const [startTime, setStartTime] = useState(new Date());
    const [reservationId, setReservationId] = useState(null);
    const [isValid, setIsValid] = useState(false);

    const handleCustomerNameChange = (newCustomerName) => {
        setCustomerName(newCustomerName);
        validateForm();
    };

    const handleNumOfHoursChange = (newNumofHours) => {
        setNumOfHours(newNumofHours);
        validateForm();
    };

    const validateForm = () => {
        if (customerName && numOfHours) {
            setIsValid(true);
        } else {
            setIsValid(false);
        }
        console.log(isValid);
    };

    const handleStartTime = (startTime) => {
        setStartTime(startTime.toLocaleString('en-US', { hour: 'numeric', minute: 'numeric', hour12: true }));
    };

    const onSelectBike = (bikeId, bikePrice) => {
        setSelectedBikeId(bikeId);
        setSelectedBikePrice(bikePrice);
    };

    async function handleSubmit() {
        const reservation = await createReservation(numOfHours, stationId, customerName, selectedBikeId);
        setReservationId(reservation.reservationId);
    }


    var bikeImages = {
        'Standard': {
            image: 'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/81ozH1S0-WL._AC_SL1500_.jpg',
        },
        'Electric': {
            image: 'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/613TloL9e3L._AC_SL1500_.jpg',
        },
        'Tandem': {
            image: 'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/81Fi6V7wciL._AC_SL1500_.jpg',
        },
        'Mountain': {
            image: 'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/91gEELfSWkL._AC_SX679_.jpg',
        },
        'City': {
            image: 'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71QTRS0M7BL._AC_SL1500_.jpg',
        },
    }

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await getBikesByStation(stationId);
                setBikes(response);
                setLoading(false);
            } catch (error) {
                console.error(error);
            }
        }
        fetchData();
    }, [stationId]);

    if (loading) {
        return <div>Loading...</div>;
    }


    function Confirmation(reservationId) {
        return (
            <React.Fragment>
                <Typography variant="h5" gutterBottom>
                    Thank you for confirming! Your reservationId is {reservationId}.
                </Typography>
            </React.Fragment>
        )
    }

    const checkoutSteps = ['Check Out', 'Review','Confirmation'];

    function getStepContent(step) {
        switch (step) {
            case 0:
                return <ReservationForm
                    onCustomerNameChange={handleCustomerNameChange}
                    onNumOfHoursChange={handleNumOfHoursChange}
                    onStartTimeChange={handleStartTime}
                />;
            case 1:
                return <CheckOut customerName={customerName} startTime={startTime}
                    startStation={stationId} pricePerHour={selectedBikePrice} />
            case 2:
                return Confirmation(reservationId);

            throw new Error('Unknown step');
        }
    }

    return (
        <>
            {selectedBikeId ? (
                <Reservation
                    steps={checkoutSteps}
                    getStepContent={getStepContent}
                    onSubmit={handleSubmit}
                    disabled={!isValid}
                />
            ) : (
                <CarouselComponent
                    bikes={bikes}
                    bikeImages={bikeImages}
                    onClick={onSelectBike}
                />
            )}
        </>
    );
}


export default CreateReservation;

