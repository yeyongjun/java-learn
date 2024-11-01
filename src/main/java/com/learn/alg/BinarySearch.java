package com.learn.alg;

import java.util.Random;

/**
 * @author yeyongjun
 * @since 2024/10/29 14:54
 */
public class BinarySearch {
    public static void main(String[] args) throws Exception{
        Random random = new Random();
        int randomValue = random.nextInt(10);
        System.out.println("randomValue:" + randomValue);
        int[] sortedArray = new int[]{1,2,3,4,5,6,7,8,9,10};
        int resultIndex = search(sortedArray, randomValue);
        System.out.println("resultIndex:" + resultIndex);
    }

    public static int search(int[] sortedArray, int num) throws Exception{
        int start = 0;
        int end = sortedArray.length - 1;
        int mid;
        while (start < end) {
            System.out.println("start=" + start + ",end="+ end);
            Thread.sleep(1000L);
            mid = (end -start) / 2 + start;
            if (sortedArray[mid] < num) {
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return start;
    }
}
