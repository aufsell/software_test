import org.example.QuickSort;
import org.example.QuickSort.PivotInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class QuickSortTest {

    @BeforeEach
    public void setUp() {
        QuickSort.getPivotInfoList().clear();
    }

    @Test
    public void testQuickSort_SimpleArray() {
        int[] arr = {4, 2, 7, 1, 3};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 7}, arr);
        List<PivotInfo> pivotInfoList = QuickSort.getPivotInfoList();
        Assertions.assertEquals(3, pivotInfoList.size());
        Assertions.assertEquals(new PivotInfo(3, 2), pivotInfoList.get(0));
        Assertions.assertEquals(new PivotInfo(1, 0), pivotInfoList.get(1));
        Assertions.assertEquals(new PivotInfo(7, 4), pivotInfoList.get(2));
    }

    @Test
    public void testQuickSort_EmptyArray() {
        int[] arr = {};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(new int[]{}, arr);
        List<PivotInfo> pivotInfoList = QuickSort.getPivotInfoList();
        Assertions.assertTrue(pivotInfoList.isEmpty());
    }

    @Test
    public void testQuickSort_SingleElement() {
        int[] arr = {42};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(new int[]{42}, arr);
        List<PivotInfo> pivotInfoList = QuickSort.getPivotInfoList();
        Assertions.assertTrue(pivotInfoList.isEmpty());
    }

    @Test
    public void testQuickSort_LargeArray() {
        int[] arr = {Integer.MAX_VALUE, Integer.MAX_VALUE-1, Integer.MAX_VALUE-2, Integer.MAX_VALUE-3, Integer.MAX_VALUE-4, Integer.MAX_VALUE-5};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(new int[]{Integer.MAX_VALUE-5, Integer.MAX_VALUE-4, Integer.MAX_VALUE-3, Integer.MAX_VALUE-2, Integer.MAX_VALUE-1, Integer.MAX_VALUE}, arr);
        List<PivotInfo> pivotInfoList = QuickSort.getPivotInfoList();
        Assertions.assertEquals(5, pivotInfoList.size());
        Assertions.assertEquals(new PivotInfo(Integer.MAX_VALUE-5, 0), pivotInfoList.get(0));
        Assertions.assertEquals(new PivotInfo(Integer.MAX_VALUE, 5), pivotInfoList.get(1));
        Assertions.assertEquals(new PivotInfo(Integer.MAX_VALUE-4, 1), pivotInfoList.get(2));
        Assertions.assertEquals(new PivotInfo(Integer.MAX_VALUE-1, 4), pivotInfoList.get(3));
        Assertions.assertEquals(new PivotInfo(Integer.MAX_VALUE-3, 2), pivotInfoList.get(4));
    }

    @Test
    public void testQuickSort_SortedArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7}, arr);
        List<PivotInfo> pivotInfoList = QuickSort.getPivotInfoList();
        Assertions.assertEquals(6, pivotInfoList.size());
        Assertions.assertEquals(new PivotInfo(7, 6), pivotInfoList.get(0));
        Assertions.assertEquals(new PivotInfo(6, 5), pivotInfoList.get(1));
        Assertions.assertEquals(new PivotInfo(5, 4), pivotInfoList.get(2));
        Assertions.assertEquals(new PivotInfo(4, 3), pivotInfoList.get(3));
        Assertions.assertEquals(new PivotInfo(3, 2), pivotInfoList.get(4));
        Assertions.assertEquals(new PivotInfo(2, 1), pivotInfoList.get(5));
    }

    @Test
    public void testQuickSort_ReverseSortedArray() {
        int[] arr = {7, 6, 5, 4, 3, 2, 1};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7}, arr);
        List<PivotInfo> pivotInfoList = QuickSort.getPivotInfoList();
        Assertions.assertEquals(6, pivotInfoList.size());
        Assertions.assertEquals(new PivotInfo(1, 0), pivotInfoList.get(0));
        Assertions.assertEquals(new PivotInfo(7, 6), pivotInfoList.get(1));
        Assertions.assertEquals(new PivotInfo(2, 1), pivotInfoList.get(2));
        Assertions.assertEquals(new PivotInfo(6, 5), pivotInfoList.get(3));
        Assertions.assertEquals(new PivotInfo(3, 2), pivotInfoList.get(4));
        Assertions.assertEquals(new PivotInfo(5, 4), pivotInfoList.get(5));
    }

    @Test
    public void testQuickSort_AllSameElements() {
        int[] arr = {5, 5, 5, 5, 5};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(new int[]{5, 5, 5, 5, 5}, arr);
        List<PivotInfo> pivotInfoList = QuickSort.getPivotInfoList();
        Assertions.assertEquals(4, pivotInfoList.size());
        Assertions.assertEquals(new PivotInfo(5, 4), pivotInfoList.get(0));
        Assertions.assertEquals(new PivotInfo(5, 3), pivotInfoList.get(1));
        Assertions.assertEquals(new PivotInfo(5, 2), pivotInfoList.get(2));
        Assertions.assertEquals(new PivotInfo(5, 1), pivotInfoList.get(3));

    }
}
