import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class BSTreeTest {
    private BSTree a;
    private BSTree b;
    private BSTree c;

    @Before
    public void init(){
        a = new BSTree<Integer>();
        b = new BSTree<String>();
        c = new BSTree<Double>();
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
        assertTrue(b.findKey("Zhao ann"));
        assertTrue(b.findKey("Hola"));
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
    }

    @Test
    public void intersection() {
    }

    @Test
    public void levelMax() {
    }
}