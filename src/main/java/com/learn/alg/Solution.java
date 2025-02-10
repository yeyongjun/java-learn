package com.learn.alg;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author yeyongjun
 * @since 2025/1/2 15:44
 */
public class Solution {
    private Map<Integer, Integer> blacklistMap;
    private Random random;
    private int range;

    public Solution(int N, int[] blacklist) {
        this.random = new Random();
        this.range = N - blacklist.length;
        this.blacklistMap = new HashMap<>();

        // 建立黑名单数字与合法数字的映射关系
        for (int b : blacklist) {
            blacklistMap.put(b, -1); // 先初始化映射为空
        }

        int last = N - 1;
        for (int b : blacklist) {
            if (b >= range) continue; // 黑名单数字大于或等于范围，不需要映射
            while (blacklistMap.containsKey(last)) {
                last--; // 找到一个合法的数字
            }
            blacklistMap.put(b, last--);
        }
        System.out.println(blacklistMap);
    }

    public int pick() {
        int rand = random.nextInt(range); // 随机生成一个0到range-1之间的数
        return blacklistMap.getOrDefault(rand, rand); // 如果rand在黑名单中，返回映射的合法数
    }

    public static void main(String[] args) {
        Solution solution = new Solution(100, new int[] {1,3,5,98,99});
        System.out.println(solution.pick());
    }

}
