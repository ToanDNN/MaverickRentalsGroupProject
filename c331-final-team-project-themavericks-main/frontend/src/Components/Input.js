import React, { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';

export function ReservationForm(props) {
    const [customerName, setCustomerName] = useState('');
    const [numOfHours, setNumOfHours] = useState('');

    const handleCustomerNameChange = (event) => {
        props.onCustomerNameChange(event.target.value)
        setCustomerName(event.target.value);
    };

    const handleNumOfHoursChange = (event) => {
        props.onNumOfHoursChange(event.target.value);
        setNumOfHours(event.target.value);
    };



    useEffect(() => {
        const interval = setInterval(() => {
            const now = new Date();
            props.onStartTimeChange(now);
        }, 1000);

        return () => {
            clearInterval(interval);
        };
    }, []);

    return (
        <div>
            <TextField
                id="customer-name"
                label="Customer Name"
                value={customerName}
                onChange={handleCustomerNameChange}
                required
            />
            <TextField
                id="num-of-hours"
                label="Number of Hours"
                type="number"
                value={numOfHours}
                onChange={handleNumOfHoursChange}
                required
            />
        </div>
    );
}