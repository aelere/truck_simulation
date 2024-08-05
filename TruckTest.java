import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TruckTest.
 *
 * @author  Lasha Geladze
 * @version 12/07/2022
 */
public class TruckTest
{   
    /**
     * Default constructor for test class TruckTest
     */
    public TruckTest()
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
    @DisplayName("test move()")
    public void test_move(){
        Warehouse w0 = new Warehouse(0, 0);   //starting warehouse
        Warehouse w1 = new Warehouse(0, 8);   
        Warehouse w2 = new Warehouse(8, 8);   //generic
        
        ArrayList<Warehouse> wList = new ArrayList<Warehouse>();
        
        wList.add(w0);
        wList.add(w1);
        wList.add(w2);
        
        Shipment s1 = new Shipment(2, w1, w2);
        PriorityQueue<Shipment> shipmentList = new PriorityQueue<Shipment>();
        shipmentList.add(s1);
        
        Truck t1 = new Truck(2,w0, 1, 1, wList);  
        t1.getManifest().setShipmentList(shipmentList); //setting up truck with speed 4, moving horizontally to pick up shipment at w1 and drop it off at w2
        //t1.setDestination(t1.getManifest().getShipmentList().element().getPickUp());
        
        t1.action();    //first action, went up by 4, still moving
        assertEquals(t1.getState(), TruckState.MOVING);
        assertEquals(t1.getLocation().getX(), 0);
        assertEquals(t1.getLocation().getY(), 4);   //moved up by 4
        
        t1.action();    //second action, went up to the warehouse, got to its location and is prepared to load.
        assertEquals(t1.getState(), TruckState.LOADING);
        assertEquals(t1.getLocation().getY(), 8);
        
        t1.action();    //loaded, preparing to move to (8,8)
        assertEquals(t1.getState(), TruckState.MOVING);
        assertEquals(t1.getStorage().peek(), s1);   //picked up the shipment.
        
        t1.action();    //at (4, 8), moving to destination
        assertEquals(t1.getLocation().getX(), 4);
        assertEquals(t1.getLocation().getY(), 8);
        assertEquals(t1.getState(), TruckState.MOVING);
        
        t1.action();    //arrived at destination, docked.
        assertEquals(t1.getLocation().getX(), 8);
        assertEquals(t1.getState(), TruckState.UNLOADING);  //since the shipment size is 2 and truck size is 2, truck is unloading instead of loading.
        
        t1.action();    //emptied, did job, now isDone.
        assertTrue(t1.getStorage().isEmpty());
        assertTrue(t1.isDone());
    }
}
