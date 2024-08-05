
/**
 * Interface for Queue.
 *
 * @author Lasha Geladze
 * @version 12/09/2022
 */
public interface QueueInterface<E>
{   /**
     * Method that returns the head node's data.
     * 
     * @return Head node data.
     */
    public E element();
    
    /**
     * The add method creates a node by the data given by the user. Then, it puts the new Node at the beginning of the list which becomes the new 
     * head node. Increments size. Returns true if the addition was successful.
     * 
     * @param item The data given by the user.
     * 
     * @return True if addition was successful.
     */
    public boolean add(E item);
    
    /**
     * The size method the size of the collection.
     * 
     * @return The size of the collection.
     */
    public int size();
    
    /**
     * Method that removes the head element of the Queue.
     */
    public E remove();
    
    /**
     * Method that converts the linked quque to an array. Head node is the last element in this representation.
     * 
     * @return The Queue turned into an array.
     */
    public E[] toArray();
}
