import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
/**
 * The test class QueueTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class QueueTest
{
    /**
     * Default constructor for test class QueueTest
     */
    public QueueTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    @DisplayName("test element() and remove()")
    public void test_element_remove(){
        Queue<Integer> q = new Queue<Integer>();
        assertEquals(q.element(), null);
        q.add(3);
        q.add(4);
        q.add(5);
        
        assertEquals(q.element(), 3);   //since queue is fifo, 3 should be element
        
        q.remove();
        assertEquals(q.element(), 4);
        q.remove();
        assertEquals(q.element(), 5);
        q.remove();
        
        assertEquals(q.element(), null);
        
        boolean exception = false;
        try{
            q.remove(); //no elements, exception should be thrown
        }catch (NoSuchElementException e){
            exception = true;
        }
        
        assertTrue(exception);
    }
    
    @Test
    @DisplayName("test add() and toArray()")
    public void test_add_toArray(){
        Queue<Integer> q = new Queue<Integer>();
        q.add(3);
        q.add(4);
        q.add(1);
        assertEquals(q.element(), 3);
        Integer[] arr = new Integer[]{3, 4, 1};
        assertArrayEquals(q.toArray(), arr);
        
        
        Integer[] arr2 = new Integer[]{3, 4, 1, 5};
        q.add(5);
        assertArrayEquals(q.toArray(), arr2);
    }
    
    @Test
    @DisplayName("test size()")
    public void test_size(){
        Queue<Integer> q = new Queue<Integer>();
        assertEquals(q.size(), 0);  //initially 0
        
        q.add(2);
        q.add(3);
        q.add(4);   //3 added
        
        assertEquals(q.size(), 3);
        
        q.remove(); //1 removed 2 added +1
        q.add(5);
        q.add(6);
        assertEquals(q.size(), 4);
        
        while(!q.isEmpty()){
            q.remove(); //all removed
        }
        assertEquals(q.size(), 0);
    }
}
