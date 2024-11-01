package com.learn.alg.sort;

import java.util.Arrays;

/**
 * @author yeyongjun
 * @since 2024/10/29 14:49
 */
public class MergeSortService implements ISortService{
    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length-1);
    }

    public void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) /2;
//        int[] array1 = Arrays.copyOfRange(array, left, mid);
//        int[] array2 = Arrays.copyOfRange(array, mid, right);
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    public void merge(int[] array, int[] array1, int[] array2) {
//        int[] array = new int[array1.length + array2.length];
        int i=0,j=0,k=0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                array[k++] = array1[i++];
            }else {
                array[k++] = array2[j++];
            }
        }
        while (i < array1.length) {
            array[k++] = array1[i++];
        }
        while (j < array2.length) {
            array[k++] = array2[j++];
        }
//        return array;
    }

    public void merge(int[] array, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        int[] tmp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (array[i] > array[j]) {
                tmp[k++] = array[j++];
            }else {
                tmp[k++] = array[i++];
            }
        }

        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= right) {
            tmp[k++] = array[j++];
        }
        for (int x=0; x< tmp.length; x++) {
            array[left+x] = tmp[x];
        }
    }
}
