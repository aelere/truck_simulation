import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * DoublyLinkedCollection is a generic class in which each element is a Node. Each Node (Node definied within this class) in DoublyLinkedCollection has
 * references to two other nodes: next and previous. Each Node also stores data that is given by the user. Every new element added is stored at the front
 * (head) of the Collection. Extends AbstractCollection<E>.
 *
 * @author Lasha Geladze
 * @version 10/25/2022
 * 
 * @see java.util.AbstractCollection
 * @see java.util.Iterator
 * @see java.util.NoSuchElementException
 * 
 */
public class Queue<E> extends AbstractCollection<E> implements QueueInterface<E>
{   
    /**
     * The head Node keeps track of the beginning of the list. Initially, head is null since no element is added. After each addition, the added
     * element becomes head.
     */
    protected Node head = null;
    
    /**
     * The size int keeps track of the size of the list.
     */
    protected int size = 0;
    
    /**
     * The Node Class stores the data given by the user, as well as has references to two nodes (previous and next) that are used to link each
     * Node in the list.
     */
    protected class Node{
        
        /**
         * The data given by the user.
         */
        E data;
        
        /**
         * Reference to the next node.
         */
        protected Node next;
        
        /**
         * Reference to the previous node
         */
        protected Node previous;
        
        /**
         * The node constructor.
         */
        public Node (E initialData, Node prevNode, Node nextNode){
            data = initialData;
            next = nextNode;
            previous = prevNode;
        }
    }
    
    public E element(){
        if (head == null){
            return null;
        }
        return head.data;
    }
    
    @Override
    public boolean add (E item){
        Node newData = new Node(item, null, null);
        if (head == null){ //if the list is empty then the new node becomes head straight away.
            head = newData;
        }else{ //replacing head with NewNode, making sure the previous head is not lost.
            Node curr = head;
            while (curr.next != null){
                curr = curr.next;
            }
            curr.next = newData;
            newData.previous = curr;
        }
        size++;
        return true;
    }
    
    @Override
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
    @Override
    public Iterator<E> iterator(){
        return new MyIterator();
    }
    
    public E remove(){
        if (head == null){
            throw new NoSuchElementException();
        }
        E temp = head.data;
        if (head.next == null){
            head = null;
        }else{
            head = head.next;
            head.previous = null;
        }
        // Iterator iter =  iterator();
        // iter.next();
        // iter.remove();
        //Node tempNode = head;
        
        size--;
        return temp;
    }
    
    @Override
    public String toString(){
        E[] itemListArray = toArray();
        StringBuffer st = new StringBuffer();
        for (E item : itemListArray){
            st.append(item + " ");
        }
        
        return st.toString();
    }
    
    public E[] toArray(){
        Iterator iter = new MyIterator();
        ArrayList<E> itemList = new ArrayList<E>();
        while (iter.hasNext()){
            itemList.add((E) iter.next());
        }
        E[] itemListArray = (E[]) itemList.toArray();
        return itemListArray;
    }
    
    /**
     * The MyIterator class is used to iterate through the DoublyLinkedCollection. It uses a Node variable (cursor) 
     * to keep track of the next node to examine. Another node variable (pending) is examined after each call. This method ensures that NullPointerException
     * is not thrown when we need to utilize cursor.previous at the end of the collection. Implements Iterator<E>
     * 
     * @see java.util.Iterator
     */
    protected class MyIterator implements Iterator<E>{
        
        /**
         * The Cursor Node keeps track of the next element to be examined in the collection. It is head initially, even when the list is empty. 
         * If the list is empty or there are no more elements, cursor is null. Otherwise, cursor points to the next element to be returned by next().
         */
        private Node cursor = head;
        
        /**
         * The pending node references the node whose removal is “pending”. If this variable is non-null, then remove() will delete 
         * the node it references. If it is null, we cannot do a remove(). Pending is initially null because of this.
         */
        private Node pending = null;
        
        /**
         * The next() method goes through the List using Cursor and Pending Nodes. If there are no additionall elements, throws NoSuchElementException.
         * Returns the data of the cursor element prior to next() call (or the pending Node that becomes the cursor once this method is called).
         * 
         * @see java.util.NoSuchElementException
         * 
         * @return The data of the next element
         */
        @Override
        public E next(){
            if (!hasNext()){   //checking if there is a next element.
                throw new NoSuchElementException();
            }
            
            pending = cursor;   
            cursor = cursor.next;
            return pending.data;
        }
        
        /**
         * Checks if there is a next element using cursor and size variables.
         * 
         * @return True if there is a next element, false if there is not.
         */
        @Override
        public boolean hasNext() { 
            return size > 0 && (cursor != null); 
        } 
        
        /**
         * Removes the pending element from the Collection. Is responsible for adjusting the nodes properly so that no Node is lost. Throws 
         * IllegalStateException() if next() has not been called prior.
         * 
         * @see java.util.IllegalStateException
         */
        @Override
        public void remove(){
            
            if (pending == null) throw new IllegalStateException();
            if (size == 1){
                head = null;    //if the element removed is the only one in the list
            }else if (pending.previous == null){
                head = head.next;
                cursor.previous = null; //if the head is removed
            }else if (cursor == null){
                Node temp = pending.previous;
                temp.next = null;   //if the last element is removed
            }else{
                Node temp = pending.previous;
                temp.next = cursor;
                cursor.previous = temp;     //if a middle element is removed
            }
            --size;
            pending = null;
        }
    }
}