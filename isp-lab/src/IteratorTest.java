import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class IteratorTest {
    private List<String> arraylist;
    private Iterator<String> iterator;

    @Before
    public void setUp() {
        arraylist = new ArrayList<>();
        arraylist.add("soen345");
        arraylist.add("soen390");
        arraylist.add("soen357");
        iterator = arraylist.iterator();
    }

    @Test //C1C5
    public void testHasNext_TT() {
        assertTrue(iterator.hasNext());
    }

    @Test //C1C5
    public void testHasNext_FT() {
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class) //C1C5
    public void testHasNext_TF() {
        arraylist.add("soen321");
        iterator.hasNext();
    }

    @Test //C1C2C5
    public void testNext_TTT() {
        assertEquals("soen345", iterator.next());
    }

    @Test(expected = NoSuchElementException.class) //C1C2C5
    public void testNext_FFT() {
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();

    }

    @Test
    public void testNext_TFT() //C1C2C5
    {
        arraylist = new ArrayList<>();
        arraylist.add(null);
        iterator = arraylist.iterator();
        assertNull(iterator.next());
    }

    @Test(expected = ConcurrentModificationException.class) //C1C2C5
    public void testNext_TFF() {
        arraylist.add("soen321");
        iterator.next();
    }



    @Test public void testRemove_TTTTT() //C1C2C3C4C5
    {
        iterator.next();
        iterator.remove();
        assertFalse (arraylist.contains ("soen345"));
    }

    @Test public void testRemove_FFTTT() //C1C2C3C4C5
    {
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertFalse (arraylist.contains ("dog"));
    }

    @Test public void testRemove_TFTTT() //C1C2C3C4C5
    {
        arraylist.add (null);
        arraylist.add ("soen321");
        iterator = arraylist.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertFalse (arraylist.contains (null));
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testRemove_TTFTT() //C1C2C3C4C5
    {
        arraylist = Collections.unmodifiableList(arraylist);
        iterator = arraylist.iterator();
        iterator.next();
        iterator.remove();
    }

    @Test (expected=IllegalStateException.class)
    public void testRemove_TTTFT() //C1C2C3C4C5
    {
        iterator.remove();
    }

    @Test (expected=ConcurrentModificationException.class)
    public void testRemove_TTTTF() //C1C2C3C4C5
    {
        iterator.next();
        arraylist.add ("soen343");
        iterator.remove();
    }





}
