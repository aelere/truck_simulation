import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class LocationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LocationTest
{   
    
    Location l1;
    Location l2;
    Location l3;
    Location l4;
    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest()
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
        l1 = new Location(0, 0);
        l2 = new Location(3, 4);
        l3 = new Location(-3, -4); //check negative location
        l4 = new Location(-3, 4);
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
    @DisplayName("test changeAngle()")
    public void test_changeAngle(){
        assertEquals(Math.round(l1.calculateAngle(l2)* 100.00) / 100.00, 0.93); //53 degrees
        assertEquals(Math.round(l1.calculateAngle(l3)* 100.00) / 100.00, 4.07); //233 degrees approximately
        assertEquals(Math.round(l1.calculateAngle(l4)* 100.00) / 100.00, 2.21); //takes proper radian values into account, 126 degrees
        assertEquals(l1.calculateAngle(l1), -100.0); // invalid input, same locations should return -100
    }
    
    @Test
    @DisplayName("test distance()")
    public void test_distance(){
        assertEquals(l1.distance(l2), 5.0); //general case
        assertEquals(l1.distance(l3), 5.0); //l2 and l3 should be same distance apart
        assertEquals(l1.distance(l1), 0.0); //distance to same location is 0
    }
    
    @Test
    @DisplayName("test toString()")
    public void test_toString(){
        assertEquals(l1.toString(), "(0.0, 0.0)");
        assertEquals(l2.toString(), "(3.0, 4.0)");
        assertEquals(l3.toString(), "(-3.0, -4.0)");
        assertEquals(l4.toString(), "(-3.0, 4.0)");
    }
}
