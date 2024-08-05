
/**
 * Interface for Stack.
 *
 * @author Lasha Geladze
 * @version 12/09/2022
 */
public interface StackInterface<E>
{   
    /**
     * Method that empties the Stack.
     */
     public void empty();
     
    /**
    * Method that returns the head Node's data.
    * 
    * @return The head node's data.
    */
    public E peek();
    
    /**
     * Method that removes the head node from the list, reassigning head to the next node (if it exists). 
     * 
     * @return The removed head node's data.
     */
    public E pop();
    
    /**
     * Method that pushes an item to the stack. The pushed item becomes head, while the previous head item is linked to the new head node.
     * 
     * @param item The data pushed onto the stack
     *
     */
    public void push(E item);
    
    /**
     * Method that checks whether the stack is empty using size and head variables.
     * 
     * @return True if stack is empty, false if not.
     */
    public boolean isEmpty();
    
    /**
     * Method that returns the amount of elements in the stack.
     * 
     * @return The amount of elements in the stack.
     */
    public int size();
    
    /**
     * Method that converts the linked stack to an array. Head node is the last element in this representation.
     * 
     * @return The Stack turned into an array.
     */
    public E[] toArray();
}
