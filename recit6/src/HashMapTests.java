import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class HashMapTests {
    HashMapInterface<Integer, String> map;
    Set<MapEntry<Integer, String>> emptySet;
    Set<MapEntry<Integer, String>> sizeOneSet;
    Set<MapEntry<Integer, String>> sizeTwoSet;
    Set<MapEntry<Integer, String>> sizeFiveSet;
    Set<MapEntry<Integer, String>> sizeSixSet;
    MapEntry<Integer, String>[] emptyArray;
    MapEntry<Integer, String>[] sizeOneArray;
    MapEntry<Integer, String>[] sizeTwoArray;
    MapEntry<Integer, String>[] sizeFiveArray;
    MapEntry<Integer, String>[] sizeSixArray;

    static MapEntry<Integer, String> a1 = new MapEntry<Integer, String>(1, "a");
    static MapEntry<Integer, String> a21 = new MapEntry<Integer, String>(21, "a");
    static MapEntry<Integer, String> a41 = new MapEntry<Integer, String>(41, "a");
    static MapEntry<Integer, String> y1 = new MapEntry<Integer, String>(1, "y");
    static MapEntry<Integer, String> z11 = new MapEntry<Integer, String>(11, "z");
    static MapEntry<Integer, String> b11 = new MapEntry<Integer, String>(11, "b");
    static MapEntry<Integer, String> b2 = new MapEntry<Integer, String>(2, "b");
    static MapEntry<Integer, String> c3 = new MapEntry<Integer, String>(3, "c");
    static MapEntry<Integer, String> d4 = new MapEntry<Integer, String>(4, "d");
    static MapEntry<Integer, String> e5 = new MapEntry<Integer, String>(5, "e");
    static MapEntry<Integer, String> c2 = new MapEntry<Integer, String>(2, "c");
    static MapEntry<Integer, String> d32 = new MapEntry<Integer, String>(32, "d");
    static MapEntry<Integer, String> e12 = new MapEntry<Integer, String>(12, "e");
    static MapEntry<Integer, String> f3 = new MapEntry<Integer, String>(3, "f");

    @Before
    public void setUp() {
        map = new HashMap<>();

        a1.setNext(b11);
        z11.setNext(d32);

        emptySet = new HashSet<>();
        sizeOneSet = new HashSet<>();
        sizeOneSet.add(a41);
        sizeTwoSet = new HashSet<>();
        sizeTwoSet.add(a1);
        sizeTwoSet.add(b11);
        sizeFiveSet = new HashSet<>();
        sizeFiveSet.add(a21);
        sizeFiveSet.add(b2);
        sizeFiveSet.add(c3);
        sizeFiveSet.add(d4);
        sizeFiveSet.add(e5);
        sizeSixSet = new HashSet<>();
        sizeSixSet.add(y1);
        sizeSixSet.add(z11);
        sizeSixSet.add(c2);
        sizeSixSet.add(d32);
        sizeSixSet.add(e12);
        sizeSixSet.add(f3);
        emptyArray = (MapEntry<Integer, String>[]) new MapEntry[10];
        sizeOneArray = (MapEntry<Integer, String>[]) new MapEntry[10];
        sizeOneArray[1] = a41;
        sizeTwoArray = (MapEntry<Integer, String>[]) new MapEntry[10];
        sizeTwoArray[1] = a1;
        sizeFiveArray = (MapEntry<Integer, String>[]) new MapEntry[21];
        sizeFiveArray[0] = a21;
        sizeFiveArray[2] = b2;
        sizeFiveArray[3] = c3;
        sizeFiveArray[4] = d4;
        sizeFiveArray[5] = e5;
        sizeSixArray = (MapEntry<Integer, String>[]) new MapEntry[21];
        sizeSixArray[1] = y1;
        sizeSixArray[2] = c2;
        sizeSixArray[3] = f3;
        sizeSixArray[11] = z11;
        sizeSixArray[12] = e12;
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullKey() {
        map.add(null, "a");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullValue() {
        map.add(1, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullKeyAndValue() {
        map.add(null, null);
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, map.size());
    }

    @Test
    public void testSizeOne() {
        map.add(1, "a");
        assertEquals(1, map.size());
    }

    @Test
    public void testSizeChained() {
        map.add(1, "a");
        map.add(11, "b");
        assertEquals(2, map.size());
    }

    @Test
    public void testSizeRegrow() {
        map.add(1, "a");
        map.add(2, "b");
        map.add(3, "c");
        map.add(4, "d");
        map.add(5, "e");
        map.add(6, "f");
        map.add(7, "g");
        assertEquals(7, map.size());
    }

    @Test
    public void testSetEmpty() {
        assertEquals(emptySet, map.getEntrySet());
    }

    @Test
    public void testSetOne() {
        map.add(41, "a");
        assertEquals(sizeOneSet, map.getEntrySet());
    }

    @Test
    public void testSetChained() {
        map.add(1, "a");
        map.add(11, "b");
        assertEquals(sizeTwoSet, map.getEntrySet());
    }

    @Test
    public void testSetResized() {
        map.add(21, "a");
        map.add(2, "b");
        map.add(3, "c");
        map.add(4, "d");
        map.add(5, "e");
        assertEquals(sizeFiveSet, map.getEntrySet());
    }

    @Test
    public void testSetChainedResized() {
        map.add(1, "y");
        map.add(11, "z");
        map.add(2, "c");
        map.add(32, "d");
        map.add(12, "e");
        map.add(3, "f");
        assertEquals(sizeSixSet, map.getEntrySet());
    }

    @Test
    public void testArrayEmpty() {
        assertArrayEquals(emptyArray, map.getBackingArray());
    }

    @Test
    public void testArrayOne() {
        map.add(41, "a");
        assertArrayEquals(sizeOneArray, map.getBackingArray());
    }

    @Test
    public void testArrayChained() {
        map.add(1, "a");
        map.add(11, "b");
        assertArrayEquals(sizeTwoArray, map.getBackingArray());
    }

    @Test
    public void testArrayResized() {
        map.add(21, "a");
        map.add(2, "b");
        map.add(3, "c");
        map.add(4, "d");
        map.add(5, "e");
        assertArrayEquals(sizeFiveArray, map.getBackingArray());
    }

    @Test
    public void testArrayChainedResized() {
        map.add(1, "y");
        map.add(11, "z");
        map.add(2, "c");
        map.add(32, "d");
        map.add(12, "e");
        map.add(3, "f");
        assertArrayEquals(sizeSixArray, map.getBackingArray());
    }
}
