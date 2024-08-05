import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ManifestTest.
 *
 * @author Lasha Geladze
 * @version 12/07/2022
 */
public class ManifestTest
{   
    Warehouse w1;
    Warehouse w2;
    Warehouse w3;
    
    Shipment s1;
    Shipment s2;
    Shipment s3;
    
    PriorityQueue<Shipment> pr;
    Truck t;
    
    Manifest m;
    /**
     * Default constructor for test class ManifestTest
     */
    public ManifestTest()
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
        
        ArrayList<Warehouse> wList = new ArrayList<Warehouse>();
        
        wList.add(w1);
        wList.add(w2);
        wList.add(w3);
        
        s1 = new Shipment(1, w1, w2);
        s2 = new Shipment(2, w1, w3);
        s3 = new Shipment(2, w1, w2);
        
        PriorityQueue<Shipment> pr = new PriorityQueue<Shipment>();
        pr.add(s1);
        pr.add(s2);
        pr.add(s3);
        
        m = new Manifest(5, 2, null, wList);
    
        t = new Truck(2, w1, 5, 3, wList);
        
        m.setTruck(t);
        m.setShipmentList(pr);
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
    @DisplayName("test removeShipment()")
    public void test_removeShipment(){
        assertEquals(m.removeShipment(), s1);   //removes based on priority.
        assertEquals(m.removeShipment(), s3);
        assertEquals(m.removeShipment(), s2);
        
        boolean exception = false;  //remove should throw an exceptiion now
        
        try{
            m.removeShipment();
        }catch (Exception e){
            exception = true;
        }
        
        assertTrue(exception);  //exception thrown when removed from empty queue
        
        
        PriorityQueue<Shipment> pr2 = new PriorityQueue<Shipment>();    //new queue to test whether priority updated after removal
        
        pr2.add(s1);
        pr2.add(s2);
        pr2.add(s3);
        
        m.setShipmentList(pr2);
        
        s3.getLocation().setXY(100, 100);
        assertEquals(m.removeShipment(), s1); //after this, priority should be updated
        
        assertEquals(m.removeShipment(), s2);   //priority updated, s2 is removed instead of s3
    }
}
