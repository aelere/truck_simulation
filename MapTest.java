import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
/**
 * The test class MapTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MapTest
{
    /**
     * Default constructor for test class MapTest
     */
    public MapTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    @DisplayName("test map()")
    public void test_map(){
        Map myMap = new Map(100, 100, 2, 2 ,2);
        ArrayList<Warehouse> w = myMap.getWarehouseList();
        ArrayList<Truck> t = myMap.getTruckList();
        
        for (Warehouse war : w){
            assertTrue(war.getLocation().getX() >= 0 && war.getLocation().getX() < 100);
            assertTrue(war.getLocation().getY() >= 0 && war.getLocation().getY() < 100);
        }
        
        for (Truck tr: t){
            boolean isInWarehouse = false;
            for (Warehouse war : w){
                if (war.getLocation().equals(tr.getLocation())){
                    isInWarehouse = true;
                }
            }
            assertTrue(isInWarehouse);
        }
    }
}
