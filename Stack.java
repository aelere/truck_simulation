import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
/**
 * Stack is a last in first out data structure. This implementation is based on a singly linked list, where the last element added/removed is always the head.
 * It mainly It supports two basic operations called push and pop. The push operation adds an element at the top of the stack, and the pop operation 
 * removes an element from the top of the stack. Generic class, implements AbstractCollection.
 *
 *
 * @see java.util.AbstractCollection
 * @see java.util.Iterator
 * @see java.util.NoSuchElementException
 * @see java.util.ArrayList
 * @see java.util.Collections
 * @see java.util.Arrays
 * 
 * @author Lasha Geladze
 * @version 12/09/2022
 */
public class Stack<E> extends AbstractCollection<E> implements StackInterface<E>
{   
    /**
     * The head node.
     */
    private Node head;
    
    /**
     * Variable that keeps track of the size of the stack.
     */
    private int size = 0;
    
    /**
     * The Node Class stores the data given by the user, as well as has references to one node (ext) that is used to link each
     * Node in the list.
     */
    protected class Node{
        
        /**
         * Next node variable
         */
        private Node next;
        
        /**
         * Generic data stored in node.
         */
        private E data;
        
        public Node(){
            data = null;
            next = null;
        }
        
        public Node(E data){
            this.data = data;
            next = null;
        }
        
        public Node(E data, Node next){
            this.data = data;
            this.next = next;
        }
    }
    
    /**
     * The MyIterator class is used to iterate through the Stack. It uses a Node variable (cursorNode) 
     * to keep track of the next node to examine. Implements Iterator<E>. Remove not implemented.
     * 
     * @see java.util.Iterator
     */
    protected class MyIterator<E> implements Iterator<E>{
        
        /**
         * The Cursor Node keeps track of the next element to be examined in the collection. It is null initially
         * Then, cursor points to the next element to be returned by next().
         */
        protected Node cursorNode = null;
        
        /**
         * The next() method goes through the List usin g Cursor. If there are no additionall elements, throws NoSuchElementException.
         * Returns the data of the cursor element after next() call.
         * 
         * @see java.util.NoSuchElementException
         * 
         * @return The data of the next element
         */
        public E next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            
            if (cursorNode == null){
                cursorNode = head;
                return (E) cursorNode.data;
            }
            
            cursorNode = cursorNode.next;
            return (E) cursorNode.data;
        }
        
        /**
         * Checks if there is a next element using cursor and   variables.
         * 
         * @return True if there is a next element, false if there is not.
         */
        public boolean hasNext(){
            return size > 0 && (cursorNode == null || cursorNode.next != null);
        }
    }
    
    public void empty(){
        head = null;
        size = 0;
    }
    
    public E peek(){
        return head.data;
    }
    
    public E pop(){
        if (head == null){
            throw new NoSuchElementException();
        }
        E temp = head.data;
        head = head.next;
        size--;
        return temp;
    }
    
    public void push(E item){
        Node temp = new Node(item, head);
        if (head == null){
            head = temp;
        }else{
            temp.next = head;
            head = temp;
        }
        size++;
    }
    
    
    public boolean isEmpty(){
        return size == 0 && head == null;
    }
    
    /**
     * Overriden toString method.
     * 
     * @return The string representation of a stack.
     */
    @Override
    public String toString(){
        E[] itemListArray = toArray();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < itemListArray.length; i++){
            s.append(itemListArray[i] + " ");
        }
        return s.toString();
    }
    public int size(){
        return size;
    }
    
    /**
     * The iterator method returns an annonymous MyIterator object that is used to iterate through the List by the methods
     * defined in the AbstractCollection<E>
     * 
     * @see java.util.AbstractCollection
     *    
     * @return The MyIterator that goes through the Linked list.
     */
    public Iterator<E> iterator(){
        return new MyIterator();
    }
    
    public E[] toArray(){
        Iterator iter = new MyIterator();
        ArrayList<E> itemList = new ArrayList<E>();
        while (iter.hasNext()){
            itemList.add((E) iter.next());
        }
        E[] itemListArray = (E[]) itemList.toArray();
        Collections.reverse(Arrays.asList(itemListArray));
        
        return itemListArray;
    }
}
