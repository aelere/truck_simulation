import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ShipmentTest.
 *
 * @author  Lasha Geladze
 * @version 12/07/2022
 */
public class ShipmentTest
{   
    
    Warehouse w1;
    Warehouse w2;
    Warehouse w3;
    
    Shipment s1;
    Shipment s2;
    Shipment s3;
    /**
     * Default constructor for test class ShipmentTest
     */
    public ShipmentTest()
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
        w1 = new Warehouse(0, 0);   //starting warehouse
        w2 = new Warehouse(3, 4);   //generic
        w3 = new Warehouse(6, 8);   //further warehouse
        
        s1 = new Shipment(1, w1, w2);
        s2 = new Shipment(2, w1, w3);
        s3 = new Shipment(2, w1, w2);
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
    @DisplayName("test compareTo()")
    public void compareTo(){
        assertEquals(s1.compareTo(s2), 5); 
        assertEquals(s2.compareTo(s1), -5);
        assertEquals(s1.compareTo(s3), 2);
        assertEquals(s3.compareTo(s1), -2);
        assertEquals(s1.compareTo(s1), 0);
        
        PriorityQueue<Shipment> pr = new PriorityQueue<Shipment>();
        pr.add(s1);
        pr.add(s2);
        pr.add(s3);
        Shipment[] sArr = new Shipment[]{s1, s3, s2};   //the smallest according to compareTo are in the head, the biggest are at the back.
        assertArrayEquals(pr.toArray(), sArr);
    }
}
