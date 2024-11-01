package com.learn.alg.sort;

import java.util.Arrays;

/**
 * @author yeyongjun
 * @since 2024/10/29 14:49
 */
public class QuickSortService implements ISortService {
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length-1);
    }

    public void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int position = partition(array, start, end);
            quickSort(array, start, position - 1);
            quickSort(array, position + 1, end);
        }
    }

    public int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int pointer = start;
        for (int i = start; i < end; i++) {
            if (array[i] <= pivot) {
                int temp = array[i];
                array[i] = array[pointer];
                array[pointer] = temp;
                pointer++;
            }
            System.out.println(Arrays.toString(array));
        }
        int temp = array[pointer];
        array[pointer] = array[end];
        array[end] = temp;
        return pointer;
    }
}
