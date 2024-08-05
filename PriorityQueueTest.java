import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PriorityQueueTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PriorityQueueTest
{
    /**
     * Default constructor for test class PriorityQueueTest
     */
    public PriorityQueueTest()
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
    @DisplayName("test add()")
    public void test_add(){
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(3);
        q.add(1);
        q.add(5);
        Integer[] arr = new Integer[]{5, 3, 1}; //should be ordered according to priority.
        assertArrayEquals(arr, q.toArray());
        
        q.add(-5);
        q.add(7);
        assertEquals(q.element(), 7); //7 should be the head
        Integer[] arr1 = new Integer[]{7, 5, 3, 1, -5};
        
        assertArrayEquals(q.toArray(), arr1);
    }
}
