import java.util.ArrayList;
/**
 * File: Shipment.java <br>
 * Project: CS150 Project 4, Fall 2022 <br>
 * Date: 12/07/2022 <br>
 * E-mail: geladzel@lafayette.edu <br>
 * Class Description: Represents the loads that trucks will be carrying. Has a load size between 1 and 3. Has its ID, as well as dropoff and pickup 
 * locations.
 *
 * @author Lasha Geladze
 * @version 12/07/2022
 */
public class Shipment implements Comparable<Shipment>
{   
    private Location location;
    
    private boolean isDelivered = false;
    /**
     * The size of the shipment.
     */
    private int size;
    
    /**
     * The Shipment ID.
     */
    private int shipmentID;
    
    /**
     * Shipment ID creator that is assigned to a shipment and incremented upon each instance.
     */
    private static int shipmentIDCounter = 0;
    
    /**
     * The carrier (warehouse or a truck) of a shipment.
     */
    private Truck carrier;
    
    /**
     * The destination of the shipment.
     */
    private Warehouse destination;
    
    /**
     * The warehouse in which the shipment is spawned in
     */
    private Warehouse pickupWarehouse;
    
    public Shipment(){}
    public Shipment(int size, Warehouse pickupWarehouse, Warehouse dropoffWarehouse){
        this.size = size;
        shipmentID = shipmentIDCounter;
        shipmentIDCounter++;
        carrier = null;
        this.pickupWarehouse = pickupWarehouse;
        location = pickupWarehouse.getLocation();
        destination = dropoffWarehouse;
    }
    
    /**
     * Method that compares two shipments based on their distance to destinations. If the distances are equal, the newest shipment (higher ID) has priority
     * and ID difference is returned. Else, distance difference is returned.
     * 
     * @param s The compared shipment
     * 
     * @return The distance difference if it is not equal to 0. Else, ID difference.
     */
    public int compareTo(Shipment s){
        double distance1 = getLocation().distance(destination.getLocation());
        double distance2 = s.getLocation().distance(s.destination.getLocation());
        
        int diff =(int) (distance2 - distance1);
        if (diff != 0){
            return diff;
        }
        return s.shipmentID - shipmentID;
    }
    
    /**
     * Method that overrides toString() and returns shipments string representation.
     * 
     * @return Shipment string represetnation.
     */
    public String toString(){  //TEST AFTER LOGS DONE
        return "Shipment ID " +shipmentID + " SIZE: " + size + " Shipping to Warehouse " + destination.getID();
    }
    
    public Warehouse getDestination(){
        return destination;
    }
    
    /**
     * Get the location of the shipment determined by the carrier or the warehouse it is in.
     * 
     * @return The location of the shipment
     */
    public Location getLocation(){
        if (carrier == null){
            if (isDelivered){
                return destination.getLocation();
            }else{
                return pickupWarehouse.getLocation();
            }
        }else{
            return carrier.getLocation();
        }
    }
    
    public Warehouse getPickUp(){
        return pickupWarehouse;
    }
    
    public static int totalShipments(){
        return shipmentIDCounter;
    }
    
    public int getID(){
        return shipmentID;
    }
    
    public int getSize(){
        return size;
    }
    
    public void setCarrier(Truck t){
        carrier = t;
    }
    
    public void setDelivered(){
        isDelivered = true;
    }
}
