package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuickSort {

    public static class PivotInfo {
        int pivotValue;
        int pivotIndex;

        public PivotInfo(int pivotValue, int pivotIndex) {
            this.pivotValue = pivotValue;
            this.pivotIndex = pivotIndex;
        }

        @Override
        public String toString() {
            return "Опорный элемент: " + pivotValue + ", Позиция: " + pivotIndex;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PivotInfo pi = (PivotInfo) obj;
            return pivotValue == pi.pivotValue && pivotIndex == pi.pivotIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(pivotValue, pivotIndex);
        }
    }

    private static final List<PivotInfo> pivotInfoList = new ArrayList<>();

    public static List<PivotInfo> getPivotInfoList() {
        return pivotInfoList;
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            pivotInfoList.add(new PivotInfo(arr[pivotIndex], pivotIndex));

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

}
