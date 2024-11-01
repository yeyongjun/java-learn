package com.learn.alg.sort;

import java.util.Arrays;

/**
 * @author yeyongjun
 * @since 2024/10/29 18:10
 */
public class HeapSortService implements ISortService{
    @Override
    public void sort(int[] array) {
        int len = array.length;
        // build MaxHeap
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(array, len, i);
        }
        System.out.println("第一次构建最大堆：" + Arrays.toString(array));
        for (int i = len - 1; i > 0; i--) {
            // Move the top of the heap to the tail of the heap in turn
            swap(array, 0, i);
            heapify(array, --len, 0);
            System.out.println("堆顶移到最后重新堆化：" + Arrays.toString(array));
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void heapify(int[] arr, int len, int i) {
//        System.out.println(Arrays.toString(arr));
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, len, largest);
        }
    }
}
