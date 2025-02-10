package com.learn.base.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yeyongjun
 * @since 2025/2/8 16:47
 */
public class 数组集合互转 {

    /**
     * 包装类型如String[] 和 List<String> 互转
     */
    public void test1() {
        String[] a = new String[]{"1","2"};
        List<String> b = Arrays.asList(a);
//        String[] c = (String[]) b.toArray(); 不规范
        String[] c = b.toArray(new String[0]);
    }

    /**
     * 基本类型如int[] 和 List<Integer> 互转
     */
    public void test2() {
        int[] a = new int[]{1,2};
        List<Integer> b = Arrays.stream(a).boxed().collect(Collectors.toList());
        int[] c = b.stream().mapToInt(Integer::intValue).toArray();
    }
}
