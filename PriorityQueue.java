
/**
 * Write a description of class PriorityQueue here.
 *
 * @author Lasha Geladze
 * @version 12/4/2022
 */
public class PriorityQueue<E extends Comparable<E>> extends Queue<E>
{
    @Override
    public boolean add(E item){
        Node newData = new Node(item, null, null);
        if (head == null){  //just adding the new element to head if our list has no elements.
            head = newData;
        }else{
            Node current = head;
            if (current.data.compareTo(item) <= 0){//before head
                newData.next = current;
                current.previous = newData;
                head = newData;
                size++;
                return true;
            }
            while (current.data.compareTo(item) > 0 && current.next != null){
                current = current.next;
            }
            if (current.next == null && current.data.compareTo(item) > 0){ //before current is the predecessor in this case.
                current.next = newData;     //if our item is greater than all of the items.
                newData.previous = current;
            }else{ //after current
                current.previous.next = newData;
                newData.previous = current.previous;
                newData.next = current;             //placed before current
                current.previous = newData;
            }
        }
        size++;
        return true;
    }
}
