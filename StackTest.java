import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collections;
/**
 * The test class StackTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StackTest
{
    /**
     * Default constructor for test class StackTest
     */
    public StackTest()
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
    @DisplayName("test empty()")
    public void test_empty(){
        
    }
    
    @Test
    @DisplayName("test push()")
    public void test_push(){
        Stack<String> st = new Stack<String>();
        assertEquals(st.size(), 0);
        st.push("B");
        st.push("A");
        String[] arr1 = new String[]{"B", "A"};
        assertArrayEquals(st.toArray(), arr1);
        assertEquals(st.size(), 2);
        st.push("ADADSwq");
        
        String[] arr2 = new String[]{"B","A","ADADSwq"};
        assertArrayEquals(st.toArray(), arr2);
        assertEquals(st.size(), 3);
        
        st.push("j");
        String[] arr3 = new String[]{"B","A","ADADSwq", "j"}; //pushing at the end
        assertArrayEquals(st.toArray(), arr3);
        assertEquals(st.size(), 4);
        
        st.push("a");
        String[] arr4 = new String[]{"B","A","ADADSwq", "j", "a"};
        assertArrayEquals(st.toArray(), arr4);
        assertEquals(st.size(), 5);
    }
    
    @Test
    @DisplayName("test pop()")
    public void test_pop(){
        Stack<String> st = new Stack<String>();
        boolean exception1 = false;
        
        try{
            st.pop();
        }catch(NoSuchElementException e){
            exception1 = true;
        }
        
        assertTrue(exception1);
        
        st.push("B");
        st.push("A");
        st.push("j");
        st.push("a");
        
        String[] arr1 = new String[]{"B", "A", "j"};
        
        assertEquals(st.pop(), "a");
        
        assertArrayEquals(st.toArray(), arr1);
        assertEquals(st.pop(), "j");
        assertEquals(st.pop(), "A");
        
        String[] arr2 = new String[]{"B"};
        assertArrayEquals(st.toArray(), arr2);
        
        assertEquals(st.pop(), "B");
    }
    
    @Test
    @DisplayName("Test size")
    public void test_size(){
        Stack<String> list = new Stack<String>();
        
        assertEquals(list.size(), 0);
        
        list.push("A");
        
        assertEquals(list.size(), 1);
        
        list.push("B");
        list.push("C");
        list.push("D");
        
        assertEquals(list.size(), 4);
        
        list.pop();
        list.pop();
        
        assertEquals(list.size(), 2);
    }
    
    @Test
    @DisplayName("test iterator()")
    public void test_iterator(){
        Stack<String> list = new Stack<String>();
        
        list.push("B");
        list.push("C");
        list.push("D");
        list.push("A");
        
        ArrayList<String> arrList = new ArrayList<String>();
        for (String element : list){    
            arrList.add(element);   //enhanced for loop calls next and hasNext to iterate, if the loop works properly then the iterator works
        }
        
        assertEquals(arrList.size(), 4);
        assertEquals(arrList.get(0), list.pop());
        assertEquals(arrList.get(1), list.pop());
        assertEquals(arrList.get(2), list.pop());
        assertEquals(arrList.get(3), list.pop());
        
    }
}
