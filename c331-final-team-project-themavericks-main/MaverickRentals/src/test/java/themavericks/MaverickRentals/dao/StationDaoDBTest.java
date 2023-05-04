package themavericks.MaverickRentals.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import themavericks.MaverickRentals.entity.Station;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StationDaoDBTest {

    @Autowired
    StationDao stationDao;

    @Test
    public void getAllStationsTest() {
        List<Station> stations = stationDao.getAllStations();
        assertNotNull(stations.size());
    }

    @Test
    public void findStationByIdTest() {
        List<Station> testStation = stationDao.findStationById(1);
        assertEquals(1, testStation.size());
        assertEquals(testStation.get(0).getStationName(), "North Moore St & Greenwich St");
    }

    @Test
    public void updateStationTest() {
        assertTrue(stationDao.updateStation(1, true));
        List<Station> stations = stationDao.findStationById(1);
        assertEquals(1, stations.get(0).getStationAvailableBikes());
        assertTrue(stationDao.updateStation(1, false));
        stations = stationDao.findStationById(1);
        assertEquals(2, stations.get(0).getStationAvailableBikes());
    }

    @Test
    public void findAllStationsTest() {
        List<Station> stations = stationDao.findAllStations();
        assertNotNull(stations.size());
    }
}