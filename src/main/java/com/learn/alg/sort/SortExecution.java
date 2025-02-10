package com.learn.alg.sort;

import java.util.Arrays;

/**
 * @author yeyongjun
 * @since 2024/10/29 16:27
 */
public class SortExecution {
    public static void main(String[] args) {
        ISortService sortService = new QuickSortService();
        int[] array = new int[]{10,7,11,3,6,4,9,8};
        System.out.println("begin array:" + Arrays.toString(array));
        sortService.sort(array);
        System.out.println("sort result：" + Arrays.toString(array));
    }
}
