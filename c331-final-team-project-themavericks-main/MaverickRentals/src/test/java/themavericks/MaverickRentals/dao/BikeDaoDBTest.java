
package themavericks.MaverickRentals.dao;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import static org.junit.Assert.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import themavericks.MaverickRentals.entity.Bike;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BikeDaoDBTest {
    
    @Autowired
    BikeDao bikeDao;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public BikeDaoDBTest() {
    }
    
//    @BeforeEach
//    public void setUp() {
//        //Empty bikes table
//        List<Bike> bikes = bikeDao.findAllBikes();
//        
//        for (Bike bike : bikes) {
//            bikeDao.deleteBikeById(bike.getBikeId());
//        }
//    }
    

    @Test
    public void testFindAvailableBikesByStationId() {
        //use 17 which has many bikes
        int stationId = 17;
        List<Bike> bikesList = bikeDao.findAvailableBikesByStationId(stationId);
        assertNotNull(bikesList);
        assertFalse(bikesList.isEmpty());
        
        for (Bike bike : bikesList) {
            assertEquals(stationId, bike.getStationId());
        }
    }


    @Test
    public void testFindAllAvailableBikes() {
        List<Bike> bikesList = bikeDao.findAllAvailableBikes();
        
        if (bikesList.isEmpty()) {
            //no available bikes
        } else {
            boolean available = true;
            for (Bike bike : bikesList) {
                assertEquals(available, bike.isAvailable());
            }        
        }
    }


    @Test
    public void testFindAllBikes() {
        List<Bike> bikesList = bikeDao.findAllBikes();
        
        assertNotNull(bikesList);
        assertFalse(bikesList.isEmpty());
    }


    @Test
    public void testUpdateBikeAvailability() throws Exception {
        boolean availableTest = true;
        int testedId = 1;
        bikeDao.updateBikeAvailability(testedId, availableTest);
        
        //check if in available bikes list
        List<Bike> bikesList = bikeDao.findAllAvailableBikes();
        //check if bike with ID 1 is in the list
        boolean madeAvailable = bikesList.stream()
                .anyMatch(b -> b.getBikeId() == testedId);
        
        assertTrue(madeAvailable);
        
    }


    @Test
    public void testUpdateBikeStatus() throws Exception {
        //testing bike id 1 which is available and at station 17
        int testBikeId = 1;
        boolean testAvailability = true;
        int stationId = 3;
        
        //change station to low pop station
        bikeDao.updateBikeStatus(testBikeId, testAvailability, stationId);
        
        //call bikes by that station and check for our bike
        List<Bike> bikesList = bikeDao.findAvailableBikesByStationId(stationId);
        assertNotNull(bikesList);
        assertFalse(bikesList.isEmpty());
        
        //Make sure our bike is in that station now
        boolean madeAvailable = bikesList.stream()
                .anyMatch(b -> b.getBikeId() == testBikeId);
        
        assertTrue(madeAvailable);
        
        //return bike to original fields
        bikeDao.updateBikeStatus(testBikeId, true, 17);
    }


    @Test
    public void testGetPricePerHour() {
        //testing bike id 1 which is an Electric bike of typeId 2
        //which has a price of 7
        int testBikeId = 1;
        BigDecimal testBikePrice = new BigDecimal(7);
        
        BigDecimal foundPricePerHour = bikeDao.getPricePerHour(testBikeId);
        
        assertEquals(testBikePrice, foundPricePerHour);
        
    }
    
}
