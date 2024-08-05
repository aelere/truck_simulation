
/**
 * Write a description of interface Schedule here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Schedule
{   
    /**
     * Prints log status of truck.
     * 
     * @String The string representation of truck log status
     */
    public String log_status();
    
    /**
     * This method does action based on the current state using switch statement. Move() if MOVING, load() if LOADING, unload if UNLOADING, 
     * wait() if WAITING.
     */
    public void action();
}
