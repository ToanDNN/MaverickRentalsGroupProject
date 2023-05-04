import React, {useState, useEffect} from 'react';
import {getAllStations, getBikesByStation} from './api/apiCalls'

import SearchBar from './components/SearchBar';
import StationSelect from './components/StationSelect';
import BikeList from './components/BikeList';

function CouldBeUseful() {

  const [stations, setStations] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const[selectedStationId, setSelectedStationId] = useState("")
  const[selectedBikeId, setSelectedBikeId] = useState("")
  const[bikes, setBikes] = useState([])

  // Get all the stations when we load the page
  useEffect(() => {
    async function fetchData() {
      const data = await getAllStations();
      setStations(data);
    }
    fetchData();
  }, []);


  useEffect(() => {
    async function fetchBikes(){
      const data = await getBikesByStation(selectedStationId)
      setBikes(data)
    }
    if(selectedStationId !== ""){
      fetchBikes()
    }
  }, [selectedStationId])

  const filteredStations = stations.filter((station) =>
    station.stationName.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleInputChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleStationSelect = (event) => {
    setSelectedStationId(event.target.value)
  }

  const handleBikeSelection = (bikeId) => {
    console.log(`Selected bike with ID ${bikeId}`);
    setSelectedBikeId(bikeId)
  };

  return (
    <div className="container my-5">
      <h1 className="mb-5">Bike id: {selectedBikeId}</h1>
      <div className="row">
        <div className="col-md-6 mb-3">
          <SearchBar searchTerm={searchTerm} handleInputChange={handleInputChange} />
        </div>
        <div className="col-md-6 mb-3">
          <StationSelect stations={filteredStations} handleStationSelect={handleStationSelect} />
        </div>
      </div>
      <div className="row">
        <div className="col">
          <BikeList bikes={bikes} handleBikeSelection={handleBikeSelection} />
        </div>
      </div>
    </div>
  );
}

export default CouldBeUseful;
