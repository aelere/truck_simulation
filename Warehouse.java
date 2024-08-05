import java.util.Random;
import java.util.ArrayList;

/**
 * File: Warehouse.java <br>
 * Project: CS150 Project 4, Fall 2022 <br>
 * Date: 12/07/2022 <br>
 * E-mail: geladzel@lafayette.edu <br>
 * Class Description: Warehouses act as destinations for trucks to unload/load the shipments. The warehouses have the two shipment lists, 
 * undelivered (which were created in the Warehouse) and delivered (which the trucks bring to the warehouse).
 * The warehouses also have a queue and an arrayList. Queue for the waiting room, and the arraylist for the dock.
 *
 * @author Lasha Geladze
 * @version 12/07/2022
 */
public class Warehouse
{   
    /**
     * The location of the warehouse.
     */
    private Location location;
    
    /**
     * The loading dock. Can only be maxDockingSpace amount of elements in it.
     */
    private ArrayList<Truck> loadingDock;
    
    /**
     * The waiting dock. Trucks are stored here while they are being loaded.
     */
    private Queue<Truck> waitingDock;
    
    /**
     * Shpments added from each manifest that are supposed to be picked up.
     */
    private ArrayList<Shipment> toBeDelivered;
    
    /**
     * Delivered shipments added in this list.
     */
    private ArrayList<Shipment> delivered;
    
    /**
     * Maximum space in the loading dock.
     */
    final static private int maxDockingSpace = 3;
    
    /**
     * Randomly generated docking space.
     */
    private int dockingSpace;
    
    /**
     * Randomizer object.
     */
    private Random r = new Random();
    
    /**
     * ID for each warehouse.
     */
    private int ID;
    
    /**
     * ID generator.
     */
    private static int IDCounter = 0;
    
    public Warehouse (double x, double y){
        Location location = new Location(x, y);
        this.location = location;
        loadingDock = new ArrayList<Truck>();
        waitingDock = new Queue<Truck>();
        dockingSpace = r.nextInt(maxDockingSpace) + 1;
        toBeDelivered = new ArrayList<Shipment>();
        delivered = new ArrayList<Shipment>();
        ID = IDCounter;
        IDCounter++;
    }
    
    public Warehouse(){}
    
    /**
     * Method that changes the truck state and keeps track of the waiting/loading docks.
     * 
     * @param t The truck that is entering the warehouse.
     *
     */
    public void getTruckIn(Truck t){
        if (hasDockingSpace()){
            loadingDock.add(t);
            if (t.isLoading()){
                t.setState(TruckState.LOADING);
            }else{
                t.setState(TruckState.UNLOADING);
            }
        }else{
            waitingDock.add(t);
            t.setState(TruckState.WAITING);
        }
    }
    
    /**
     * Method that changes the truck state upon departure and ensures that the next truck is entered in the dock.
     * 
     * @param t The truck that is leaving the warehouse.
     */
    public void getTruckOut(Truck t){
        loadingDock.remove(t);
        t.setState(TruckState.MOVING);
        if (!waitingDock.isEmpty()){
            Truck temp = waitingDock.remove();
            getTruckIn(temp);
        }
    }
    
    /**
     * Method that adds a shipment to the delivered list.
     */
    public void deliver(Shipment s){
        delivered.add(s);
    }
    
    public Location getLocation(){
        return location;
    }
    
    public void initializeShipments(Shipment s){
        toBeDelivered.add(s);
    }
    
    public int getID(){
        return ID;
    }
    
    /**
     * Method that checks whether warehouse has docking space or not
     * 
     * @return True if has space, false if not.
     */
    public boolean hasDockingSpace(){
        return loadingDock.size() < dockingSpace;
    }
    
    public void removeShipment(Shipment s){
        toBeDelivered.remove(s);
    }
    
    public void addShipment(Shipment s){
        delivered.add(s);
    }
    
    public ArrayList<Shipment> getToBeDelivered(){
        return toBeDelivered;
    }
    
    /**
     * Deleting the shipments from the delivered list (testing purposes only).
     */
    public void deleteToBeDelivered(){
        for (Shipment ship : toBeDelivered){    //clear shipments delivered by randomized manifest
            toBeDelivered.remove(ship);
        }
    }
    
    public ArrayList<Shipment> getDelivered(){
        return delivered;
    }
    
    public String toString(){
        return "Warehouse ID " + ID + " Location: " + location;
    }
    
    /**
     * Prints information for log.
     * 
     * @return Information for log in string.
     */
    public String log_status(){
        StringBuffer buff1 = new StringBuffer();
        
        buff1.append("(");
        if (toBeDelivered.isEmpty()){
            buff1.append("Empty");  //if tobedelivered is empty
        }else{
            for (Shipment s : toBeDelivered){
                buff1.append(s.getID() + ",");  //all the shipment id in toBeDelivered
            }
        }
        buff1.append(") ");
        
        StringBuffer buff2 = new StringBuffer();
        buff2.append("(");
        
        if (delivered.isEmpty()){
            buff2.append("Empty");
        }else{
            for (Shipment s : delivered){
                buff2.append(s.getID() + ",");  //all the shipment id in delivered
            }
        }
        buff2.append(") ");
        
        String toBeDeliveredString = buff1.toString();
        String deliveredString = buff2.toString();
        
        StringBuffer buff3 = new StringBuffer();
        
        buff3.append("(");
        if (loadingDock.isEmpty()){
            buff3.append("Empty");
        }else{
            for (Truck t : loadingDock){
                buff3.append(t.getID() + ",");  //all the trucks in the dock.
            }
        }
        buff3.append(") ");
        
        StringBuffer buff4 = new StringBuffer();
        buff4.append("(");
        if (waitingDock.isEmpty()){
            buff4.append("Empty");
        }else{
            for (Truck t : waitingDock){
                buff4.append(t.getID() + ",");  //all the trucks in the waiting dock.
            }
        }
        buff4.append(") ");
        String loadingDockString = buff3.toString();
        String waitingDockString = buff4.toString();
        
        return "Warehouse " + ID + " pickup ID: " + toBeDeliveredString + " delivered ID: " + deliveredString + " Docking: " + loadingDockString + " Waiting: " + waitingDockString;
    }
    
    public void setDockingSpace(int x){
        dockingSpace = x;
    }
}
