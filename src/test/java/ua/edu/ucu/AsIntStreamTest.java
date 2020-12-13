package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;


public class AsIntStreamTest {
    private IntStream stream;
    private IntStream oneElementStream;
    private IntStream empty;

    @Before
    public void init() {
        int[] array = {1, 3, 5, 7, 9};
        int[] oneElArray = {1};
        int[] emptyArr = {};
        this.stream = AsIntStream.of(array);
        this.oneElementStream = AsIntStream.of(oneElArray);
        this.empty = AsIntStream.of(emptyArr);
    }

    @Test
    public void testAverage() {
        double expResult = 5.0;
        double actualResult = stream.average();
        assertEquals(expResult, actualResult, 0.001);
    }
    @Test
    public void testAverageOneElement() {
        double expResult = 1;
        double actualResult = oneElementStream.average();
        assertEquals(expResult, actualResult, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty() {
        empty.average();
    }

    @Test
    public void testMax() {
        int expResult = 9;
        int actualResult = stream.max();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testMaxOneElement() {
        int expResult = 1;
        int actualResult = oneElementStream.max();
        assertEquals(expResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmpty() {
        empty.max();
    }

    @Test
    public void testMin() {
        int expResult = 1;
        int actualResult = stream.min();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testMinOneElement() {
        int expResult = 1;
        int actualResult = oneElementStream.min();
        assertEquals(expResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmpty() {
        empty.min();
    }

    @Test
    public void testCount() {
        int expResult = 5;
        long actualResult = stream.count();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testCountOneElement() {
        int expResult = 1;
        long actualResult = oneElementStream.count();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testCountEmpty() {
        int expResult = 0;
        long actualResult = empty.count();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testSum() {
        int expResult = 25;
        int actualResult = stream.sum();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testSumOneElement() {
        int expResult = 1;
        int actualResult = oneElementStream.sum();
        assertEquals(expResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumEmpty() {
        empty.sum();
    }

    @Test
    public void toArray() {
        int[] expResult = {1, 3, 5, 7, 9};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void toArrayOneElement() {
        int[] expResult = {1};
        int[] actualResult = oneElementStream.toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void toArrayEmpty() {
        int[] expResult = {};
        int[] actualResult = empty.toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testForEach() {
        String expResult = "13579";
        StringBuilder str = new StringBuilder();
        stream.forEach(x -> str.append(x));
        String actualResult = str.toString();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testForEachOneElement() {
        String expResult = "1";
        StringBuilder str = new StringBuilder();
        oneElementStream.forEach(x -> str.append(x));
        String actualResult = str.toString();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testForEmpty() {
        String expResult = "";
        StringBuilder str = new StringBuilder();
        empty.forEach(x -> str.append(x));
        String actualResult = str.toString();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testFilter() {
        int[] expResult = {3, 5, 7};
        int[] actualResult = stream.filter(x -> x > 1).filter(x -> x <= 7).toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testFilterOneElement() {
        int[] expResult = {1};
        int[] actualResult = oneElementStream.filter(x -> x >= 1).toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testFilterEmpty() {
        int[] expResult = {};
        int[] actualResult = empty.filter(x -> x > 1).filter(x -> x < 9).toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testMap() {
        int[] expResult = {-1, 7, 23, 47, 79};
        int[] actualResult = stream.map(x -> x * x).map(x -> x - 2).toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testMapOneElement() {
        int[] expResult = {-1};
        int[] actualResult = oneElementStream.map(x -> x * x).map(x -> x - 2).toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testMapEmpty() {
        int[] expResult = {};
        int[] actualResult = empty.map(x -> x * x).map(x -> x - 2).toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testFlatMap() {
        int[] expResult = {8, 9, 10};
        int[] actualResult = stream.filter(x -> x > 7).flatMap
                (x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testFlatMapOneElement() {
        int[] expResult = {0, 1, 2};
        int[] actualResult = oneElementStream.flatMap
                (x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testFlatMapEmpty() {
        int[] expResult = {};
        int[] actualResult = empty.flatMap
                (x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testReduce() {
        int expResult = 25;
        int actualResult = stream.reduce(0, (sum, x) -> sum += x);
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testReduceOneElement() {
        int expResult = 1;
        int actualResult = oneElementStream.reduce(0, (sum, x) -> sum += x);
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testReduceEmpty() {
        int expResult = 0;
        int actualResult = empty.reduce(0, (sum, x) -> sum += x);
        assertEquals(expResult, actualResult);
    }

}
