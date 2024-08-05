import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class WarehouseTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WarehouseTest
{   
    Warehouse w1;
    
    Truck t1;
    Truck t2;
    Truck t3;
    
    /**
     * Default constructor for test class WarehouseTest
     */
    public WarehouseTest()
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
    @DisplayName("test getTruckIn")
    public void test_getTruckIn(){
        Warehouse w1 = new Warehouse(5, 5);
        w1.setDockingSpace(3);  //setting it to 3 to not allow randomization.
        
        assertTrue(w1.hasDockingSpace());
        
        Truck t1 = new Truck();
        Truck t2 = new Truck();
        Truck t3 = new Truck();
        Truck t4 = new Truck();
        Truck t5 = new Truck();
        
        Shipment s1 = new Shipment();
        Shipment s2 = new Shipment();
        Shipment s3 = new Shipment();
        Shipment s4 = new Shipment();
        Shipment s5 = new Shipment();
        
        ArrayList<Truck> truckList = new ArrayList<Truck>();
        
        truckList.add(t1);
        truckList.add(t2);
        truckList.add(t3);
        truckList.add(t4);
        truckList.add(t5);
        
        for (Truck t : truckList){
            //giving each truck manifest a shipment to avoid nullpointerexception
            w1.getTruckIn(t);
        }
        
        assertEquals(t1.getState(), TruckState.LOADING);    //first 3 trucks are loading since they are created with an empty constructor
        assertEquals(t2.getState(), TruckState.LOADING); 
        assertEquals(t3.getState(), TruckState.LOADING); 
        
        assertFalse(w1.hasDockingSpace());  //no docking space left.
        
        assertEquals(t4.getState(), TruckState.WAITING); //last two are waiting.
        assertEquals(t5.getState(), TruckState.WAITING); 
    }
    
    @Test
    @DisplayName("test getTruckOut")
    public void test_getTruckOut(){
        Warehouse w1 = new Warehouse(5, 5);
        w1.setDockingSpace(1);  //setting it to 1 to not allow randomization.
        
        assertTrue(w1.hasDockingSpace());
        
        Truck t1 = new Truck();
        Truck t2 = new Truck();
        Truck t3 = new Truck();
        
        
        Shipment s1 = new Shipment();
        Shipment s2 = new Shipment();
        Shipment s3 = new Shipment();
        Shipment s4 = new Shipment();
        Shipment s5 = new Shipment();
        
        ArrayList<Truck> truckList = new ArrayList<Truck>();
        
        truckList.add(t1);
        truckList.add(t2);
        truckList.add(t3);
        
        for (Truck t : truckList){
            w1.getTruckIn(t);
        }
        
        assertEquals(t1.getState(), TruckState.LOADING);
        assertEquals(t2.getState(), TruckState.WAITING);
        assertEquals(t3.getState(), TruckState.WAITING);
        for (Truck t : truckList){
            w1.getTruckOut(t);
        }
        assertEquals(t1.getState(), TruckState.MOVING);
        assertEquals(t1.getState(), TruckState.MOVING);
        assertEquals(t1.getState(), TruckState.MOVING);
    }
}
