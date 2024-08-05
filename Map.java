import java.util.Random;
import java.util.ArrayList;
/**
 * File: Map.java <br>
 * Project: CS150 Project 4, Fall 2022 <br>
 * Date: 12/09/2022 <br>
 * E-mail: geladzel@lafayette.edu <br>
 * Class Description: The map creates given amount of warehouses at random locations (takes warehousenum,  and stores them in an array based on their ID.
 * Then, the trucks are placed in random warehouses. With trucks, manifests are created, which places shipments in random warehouses. Map stores active 
 * trucks and passes it to Clock.
 *
 * @author Lasha Geladze
 * @version 12/09/2022
 */
public class Map
{   
    
    /**
     * The amount of warehouses.
     */
    private int warehouseAmount; 
    
    /**
     * The amount of trucks.
     */
    private int truckAmount;
    
    /**
     * The shipment amount per each truck
     */
    private int maxShipmentAmount;
    
    /**
     * The list of trucks.
     */
    private ArrayList<Truck> truckList;
    
    /**
     * The list of Warehouses.
     */
    private ArrayList<Warehouse> warehouseList;
    
    /**
     * X coordinate size.
     */
    private int x_size;
    
    /**
     * Y coordinate size.
     */
    private int y_size;
    
    public Map(int x_size, int y_size, int warehouseAmount, int truckAmount, int maxShipmentAmount){
        this.x_size = x_size;
        this.y_size = y_size;
        this.warehouseAmount = warehouseAmount;
        this.truckAmount = truckAmount;
        this.maxShipmentAmount = maxShipmentAmount;
        
        
        Random r = new Random();
        
        warehouseList = new ArrayList<Warehouse>();
        for (int i = 0; i < warehouseAmount; i++){
            int x = r.nextInt(x_size + 1);
            int y = r.nextInt(y_size + 1);
            
            for (Warehouse w : warehouseList){
                while ((w.getLocation().getX() == x && w.getLocation().getY() == y)){
                    x = r.nextInt(x_size);
                    y = r.nextInt(y_size);
                }
            }
            
            Warehouse temp = new Warehouse((double) x, (double) y);
            warehouseList.add(temp);
        }
        
        truckList = new ArrayList<Truck>();
        for (int i = 0; i < truckAmount; i++){
            Warehouse w = warehouseList.get(r.nextInt(warehouseAmount));
            int truckSize = r.nextInt(Truck.getMaxTruckSize()) + 1;
            truckList.add(new Truck(truckSize, w, maxShipmentAmount, warehouseAmount, warehouseList));
        }
    }
    
    public ArrayList<Warehouse> getWarehouseList(){
        return warehouseList;
    }
    
    public ArrayList<Truck> getTruckList(){
        return truckList;
    }
}
