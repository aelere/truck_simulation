/**
 * File: Location.java <br>
 * Project: CS150 Project 4, Fall 2022 <br>
 * Date: 12/07/2022 <br>
 * E-mail: geladzel@lafayette.edu <br>
 * Class Description: This class stores the location of Warehouse, Truck, and Shipment variables. Stores x and y coordinates. Is responsible for calculating
 * distance and angle between two locations.
 *
 * @author Lasha Geladze
 * @version 12/07/2022
 */
public class Location
{   
    /**
     * X coordinate.
     */
    private double x;
    
    /**
     * Y coordinate
     */
    private double y;
    
    public Location(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Method that counts the distance between two coordinates.
     * 
     * @param l The location to which distance is counted.
     * 
     * @return The distance
     */
    public double distance(Location l){
        double xDiff = l.x - x;
        double yDiff = l.y - y;
        
        double dist = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));   //pythagorean theorem
        return dist;
    }
    
    /**
     * Method that counts the angle between two coordinates using angle properties in a right triangle.
     * 
     * @param l The location to which the angle is counted.
     * 
     * @return The radian value of the angle
     */
    public double calculateAngle(Location l){
        if (l.x - x == 0){
            if (l.y - y < 0){
                return -Math.PI / 2;    //since we are using arctan, this edge case required
            }else if (l.y - y > 0){
                return Math.PI / 2;
            }else{
                return -100.0;  //same location, angle not defined for exact same location
            }
        }
        if (l.x < x){
            return Math.atan((l.y - y) / (l.x - x)) + Math.PI;  //when x difference is negative, add this case to count sin and cos properly
        }
        
        return Math.atan((l.y - y) / (l.x - x));
    }
    
    /**
     * Method that adds given double values to the coordinates of the location. No matter the given value, always rounds the result to two
     * decimal places.
     * 
     * @param x The increase in x coordinate
     * @param y The increase in y coordinate
     */
    public void move(double x, double y){
        this.x += x;
        this.y += y;
        round();
    }
    
    /**
     * Method that overrides toString to represent location as (x, y).
     * 
     * @return String The string representation of the location.
     */
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    
    /**
     * Method that overrides equals() to compare x and y coordinates and see whether they match.
     * 
     * @param l The location to which the object is compared to
     * 
     * @return True if coordinates match, false if not.
     */
    public boolean equals(Location l){
        return l.getX() == x && l.getY() == y;
    }
    
    /**
     * Helper method that rounds the x and y values of location to two decimal places.
     */
    private void round(){
        x = Math.round(x * 100.00) / 100.00;
        y = Math.round(y * 100.00) / 100.00;
    }
    
    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
        round();
    }
    
    public void setXY(Location l){
        this.x = l.x;
        this.y = l.y;
        round();
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
}
