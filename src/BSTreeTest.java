import com.sun.org.apache.bcel.internal.generic.ILOAD;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BSTreeTest {
    private BSTree a;
    private BSTree b;
    private BSTree c;

    @Before
    public void init(){
        a = new BSTree<Integer>();
        b = new BSTree<String>();
        c = new BSTree<Integer>();
    }

    @Test
    public void getRoot() {
        assertEquals(null, a.getRoot());
        a.insert(10);
        assertEquals(10, a.getRoot().getKey());
        assertEquals(null, b.getRoot());
        b.insert("Hello");
        assertEquals("Hello", b.getRoot().getKey());
    }

    @Test
    public void getSize() {
        assertEquals(0, a.getSize());
        a.insert(10);
        assertEquals(1, a.getSize());
        for (int i = 0;i<100;i++){
            b.insert("Hi" + i);
        }
        assertEquals(100, b.getSize());
    }

    @Test
    public void insert() {
        a.insert(20);
        a.insert(10);
        a.insert(5);
        a.insert(15);
        assertTrue(a.findKey(15));
        assertEquals(20, a.getRoot().getKey());
        assertEquals(4, a.getSize());
        assertFalse(a.insert(15));
        b.insert("Hello");
        b.insert("bonjour");
        b.insert("Hola");
        b.insert("Zhao ann");
        b.insert("Konichiwa");
        b.insert("annyeonghaseyo");
        assertTrue(b.findKey("Zhao ann"));
        assertTrue(b.findKey("Hola"));
        assertFalse(b.insert("Hola"));
        assertEquals(6, b.getSize());
        assertEquals("Hello", b.getRoot().getKey());
    }

    @Test
    public void findKey() {
        a.insert(20);
        a.insert(10);
        a.insert(5);
        a.insert(15);
        assertTrue(a.findKey(15));
        assertFalse(a.findKey(100));
        assertFalse(a.findKey(60));
        b.insert("Hello");
        b.insert("bonjour");
        b.insert("Hola");
        b.insert("Zhao ann");
        b.insert("Konichiwa");
        b.insert("annyeonghaseyo");
        b.insert("jon-favreau");
        assertTrue(b.findKey("Zhao ann"));
        assertTrue(b.findKey("Hola"));
        assertTrue(b.findKey("jon-favreau"));
        assertFalse(b.findKey("robert-downey-jr"));
    }

    @Test
    public void insertDataAndFindDataList() {
        a.insert(20);
        a.insert(10);
        a.insert(5);
        a.insert(15);
        a.insertData(15, "Matthews0");
        a.insertData(15, "Matthews1");
        a.insertData(15, "Matthews2");
        a.insertData(15, "Matthews3");
        a.insertData(15, "Matthews4");
        a.insertData(15, "Matthews5");
        a.insertData(15, "Matthews6");
        a.insertData(15, 2);
        LinkedList test = new LinkedList<>();
        for (int i = 0;i<7;i++){
            test.add("Matthews" + i);
        }
        test.add(2);
        assertEquals(test, a.findDataList(15));
        LinkedList test2 = new LinkedList<>();
        test2.add(100);
        a.insertData(10, 100);
        assertEquals(test2, a.findDataList(10));
        b.insert("Hello");
        b.insertData("Hello", 1000000);
        LinkedList test3 = new LinkedList<>();
        test3.add(1000000);
        assertEquals(test3, b.findDataList("Hello"));
    }


    @Test
    public void findHeight() {
        a.insert(20);
        a.insert(10);
        a.insert(70);
        a.insert(5);
        a.insert(15);
        a.insert(2);
        a.insert(3);
        a.insert(50);
        a.insert(80);
        assertEquals(4, a.findHeight());
        for (int i = 100;i<1000;i++){
            a.insert(i);
        }
        assertEquals(902, a.findHeight());
        assertEquals(-1, b.findHeight());
        b.insert("hello");
        assertEquals(0, b.findHeight());
        b.insert("bonjou");
        assertEquals(1, b.findHeight());
        c.insert(100);
        c.insert(20);
        c.insert(30);
        c.insert(120);
        c.insert(130);
        c.insert(131);
        c.insert(132);
        c.insert(133);
        c.insert(134);
        c.insert(31);
        c.insert(19);
        c.insert(17);
        c.insert(15);
        c.insert(14);
        c.insert(13);
        c.insert(12);
        c.insert(11);
        c.insert(10);
        assertEquals(9, c.findHeight());
    }

    @Test
    public void iterator() {
        a.insert(20);
        a.insert(10);
        a.insert(70);
        a.insert(5);
        a.insert(15);
        a.insert(2);
        a.insert(3);
        a.insert(50);
        a.insert(80);
        Iterator<Integer> aIter = a.iterator();
        assertTrue(aIter.hasNext());
        assertEquals(new Integer(2), aIter.next());
        assertEquals(new Integer(3), aIter.next());
        assertEquals(new Integer(5), aIter.next());
        assertEquals(new Integer(10), aIter.next());
        assertEquals(new Integer(15), aIter.next());
        assertEquals(new Integer(20), aIter.next());
        assertEquals(new Integer(50), aIter.next());
        assertEquals(new Integer(70), aIter.next());
        assertTrue(aIter.hasNext());
        assertEquals(new Integer(80), aIter.next());
        assertFalse(aIter.hasNext());
        Iterator<String> bIter = b.iterator();
        assertFalse(bIter.hasNext());
        b.insert("Hello");
        b.insert("Hell");
        b.insert("Helloo");
        Iterator<String> bIter2 = b.iterator();
        assertTrue(bIter2.hasNext());
        assertEquals("Hell", bIter2.next());
        assertEquals("Hello", bIter2.next());
        assertEquals("Helloo", bIter2.next());
        assertFalse(bIter2.hasNext());
    }

    @Test
    public void intersection() {
        a.insert(20);
        a.insert(10);
        a.insert(70);
        a.insert(5);
        a.insert(15);
        a.insert(2);
        a.insert(3);
        a.insert(50);
        a.insert(80);
        c.insert(20);
        c.insert(10);
        c.insert(70);
        c.insert(3);
        c.insert(50);
        c.insert(80);
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(3, 10, 20, 50, 70, 80));
        Iterator aIter = a.iterator();
        Iterator cIter = c.iterator();
        assertEquals(test, a.intersection(aIter, cIter));
        ArrayList<Integer> test2 = new ArrayList<>();
        assertEquals(test2, a.intersection(aIter, cIter));
        c.insert(100000000);
        a.insert(100000001);
        c.insert(15);
        test.add(15);
        Collections.sort(test);
        assertEquals(test, a.intersection(a.iterator(), c.iterator()));
    }

    @Test
    public void levelMax() {
        a.insert(20);
        a.insert(10);
        a.insert(70);
        a.insert(5);
        a.insert(15);
        a.insert(2);
        a.insert(3);
        a.insert(50);
        a.insert(80);
        assertEquals(20, a.levelMax(0));
        assertEquals(70, a.levelMax(1));
        assertEquals(80, a.levelMax(2));
        assertEquals(2, a.levelMax(3));
        assertEquals(3, a.levelMax(4));
        c.insert(100);
        c.insert(20);
        c.insert(30);
        c.insert(120);
        c.insert(130);
        c.insert(131);
        c.insert(132);
        c.insert(133);
        c.insert(134);
        c.insert(1000);
        c.insert(31);
        c.insert(19);
        c.insert(17);
        c.insert(15);
        c.insert(14);
        c.insert(13);
        c.insert(12);
        c.insert(11);
        c.insert(10);
        assertEquals(130, c.levelMax(2));
        assertEquals(null, c.levelMax(10));
        assertEquals(1000, c.levelMax(7));
    }

    @Test (expected = NullPointerException.class)
    public void insertNPE1(){
        a.insert(null);
    }

    @Test (expected = NullPointerException.class)
    public void findkeyNPE(){
        a.insert(10);
        a.findKey(null);
    }

    @Test (expected = NullPointerException.class)
    public void insertdataNPE1(){
        a.insert(10);
        a.insertData(null, 100);
    }

    @Test (expected = NullPointerException.class)
    public void insertdataNPE2(){
        a.insert(10);
        a.insertData(10, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void insertDataIAE(){
        a.insert(10);
        a.insertData(100, 200);
    }

    @Test (expected = NullPointerException.class)
    public void finddatalistNPE(){
        a.insert(10);
        a.findDataList(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void finddatalistIAE(){
        a.insert(10);
        a.findDataList(100);
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratornextNSEE(){
        a.insert(100);
        a.insert(20);
        Iterator aIter = a.iterator();
        aIter.next();
        aIter.next();
        aIter.next();
    }
}