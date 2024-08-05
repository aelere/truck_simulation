import java.lang.Math;
import java.util.ArrayList;
import java.util.Random; 
import java.util.NoSuchElementException;

/**
 * File: Manifest.java <br>
 * Project: CS150 Project 4, Fall 2022 <br>
 * Date: 12/07/2022 <br>
 * E-mail: geladzel@lafayette.edu <br>
 * Class Description: Manifest creates shipments and gives them their pickup and dropoff warehouses. Manifest gives truck its shipmens by popping 
 * it from the priorityQueue. Manifest is made for each truck individually. It is used to put shipments in trucks.
 * 
 * @see java.lang.Math
 * @see java.util.ArrayList
 * @see java.util.Random
 *
 * @author Lasha Geladze
 * @version 12/07/2022
 */
public class Manifest
{   
    /**
     * PriorityQueue that tracks shipments. Every time shipment is added or removed, the list is rewriteen and updated.
     */
    private PriorityQueue<Shipment> shipmentList;
    
    /**
     * Max shipment per truck.
     */
    private int maxShipmentAmount;
    
    /**
     * Max shipment size.
     */
    final private static int maxShipmentSize = 3;
    
    /**
     * Randomly set shipment size.
     */
    private int shipmentSize;
    
    /**
     * Warehouse amount that is taken from the list.
     */
    private int warehouseAmount;
    
    private Truck t;
    
    public Manifest() {}
    
    public Manifest(int maxShipmentAmount, int truckSize, Truck t, ArrayList<Warehouse> warehouseList){
        if (truckSize >= maxShipmentSize){
            shipmentSize = maxShipmentSize;   //if truck size is bigger than maxShipmentSize, shipment of size 3 is allowed
        }else{
            shipmentSize = truckSize;   //if not, then the shipment must be smaller or equal to the truck.
        }
        this.maxShipmentAmount = maxShipmentAmount;
        
        shipmentList = new PriorityQueue<Shipment>();
        
        this.warehouseAmount = warehouseList.size();
        
        this.t = t;
        Random r = new Random();    //randomizer that randomizes shipment amount.
        
        for (int i = 1; i <= maxShipmentAmount; i++){
            int dropOffInt = r.nextInt(warehouseAmount);
            int pickUpInt;  //ints used to randomize pickup and dropoff warehouses
            do{
                pickUpInt = r.nextInt(warehouseAmount);
            }while (pickUpInt == dropOffInt);   //making sure that shipment is not picked up and dropped off in the same warehouse
            
            Warehouse w1 = warehouseList.get(pickUpInt);
            
            Warehouse w2 = warehouseList.get(dropOffInt);
            
            int sizeShipment = r.nextInt(shipmentSize) + 1; 
            
            Shipment ship = new Shipment(sizeShipment, w1, w2);
            
            w1.initializeShipments(ship);
            
            shipmentList.add(ship);
        }
    }
    
    /**
     * Method that removes a shipment from priorityqueue and updates the priority in the list.
     * 
     * @return The removed shipment
     */
    public Shipment removeShipment(){
        Shipment temp;
        if (shipmentList.isEmpty()){
            throw new NoSuchElementException();
        }
        temp = shipmentList.remove();
        PriorityQueue<Shipment> tempList = new PriorityQueue<Shipment>();   //queue is remade to take priority into account.
        for (Shipment shipment : shipmentList){
            tempList.add(shipment);
        }
        shipmentList = tempList;
        return temp;
    }
    
    /**
     * Method that returns the head element of the shipment queue.
     * 
     * @return The first element of the shipment.
     */
    public Shipment peek(){
        if (shipmentList.element() == null){
            return null;
        }
        return shipmentList.element();
    }
    
    public PriorityQueue<Shipment> getShipmentList(){
        return shipmentList;
    }
    
    public void setShipmentList(PriorityQueue<Shipment> list){    
        if (!list.isEmpty()){
            shipmentList = list;
            t.setDestination(list.element().getPickUp());
        }
    }
    
    public void setTruck(Truck truck) {
        this.t = truck;
    }
}
