package com.learn.alg.sort;

import java.util.Arrays;

/**
 * @author yeyongjun
 * @since 2024/10/29 14:50
 */
public class BubbleSortService implements ISortService{
    @Override
    public void sort(int[] array) {
        for (int i=0; i< array.length; i++) {
            for (int j=array.length-1; j>i; j--) {
                if (array[j] < array[j-1]) {
                    int tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                }
                System.out.println("冒泡：" + Arrays.toString(array));
            }
        }
    }
}
