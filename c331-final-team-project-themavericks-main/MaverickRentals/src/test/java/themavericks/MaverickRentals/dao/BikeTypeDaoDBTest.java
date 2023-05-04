
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
import themavericks.MaverickRentals.entity.BikeType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BikeTypeDaoDBTest {
    
    @Autowired
    BikeTypeDao bikeTypeDao;
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public BikeTypeDaoDBTest() {
    }
    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }


    @Test
    public void testGetAllBikeTypes() {
        List<BikeType> bikeTypes = bikeTypeDao.getAllBikeTypes();
        assertNotNull(bikeTypes);
        assertFalse(bikeTypes.isEmpty());
    }

    @Test
    public void testGetBikeTypeById() {
        BikeType testBikeType = new BikeType();
        //create new bike to test against the standard type
        BigDecimal bigFive = new BigDecimal(5);
        testBikeType.setBikePrice(bigFive);
        testBikeType.setTypeName("Standard");
        testBikeType.setBikeTypeId(1);
        
        //Retrieve a standard bike type
        BikeType retrievedBikeType = bikeTypeDao.getBikeTypeById(1);
        
        assertEquals(retrievedBikeType.getBikePrice(), testBikeType.getBikePrice());
        assertEquals(retrievedBikeType.getTypeName(), testBikeType.getTypeName());
        assertEquals(retrievedBikeType.getBikeTypeId(), testBikeType.getBikeTypeId());
        
    }
    
}
