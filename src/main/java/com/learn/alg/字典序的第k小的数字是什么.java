package com.learn.alg;

/**
 * @author yeyongjun
 * @since 2025/1/16 11:20
 */
public class 字典序的第k小的数字是什么 {
    public static void main(String[] args) {
        System.out.println(findKthNumber(100, 9)); // 1,10,100,11,12,13,14,15,16,17,18,19
    }
    public static int findKthNumber(int n, int k) {
        int curr = 1; // 从字典树的根节点开始
        k--; // 因为根节点1已经占了一位，k要减1
        while (k > 0) {
            int steps = calculateSteps(n, curr, curr + 1);
            if (steps <= k) {
                // 如果当前节点下的所有数字数量小于等于k
                // 说明第k个数字不在当前这个分支
                curr++; // 跳到下一个兄弟节点
                k -= steps; // 减去跳过的数字数量
            } else {
                // 如果当前节点范围内的数字超过了k
                // 说明第k个数字在当前分支
                curr *= 10; // 往子节点深入
                k--; // 减去当前节点本身
            }
        }
        return curr;
    }

    // 计算以prefix开头的数字的数量
    private static int calculateSteps(int n, long prefix, long nextPrefix) {
        int steps = 0;
        while (prefix <= n) {
            steps += Math.min(n + 1, nextPrefix) - prefix;
            prefix *= 10;
            nextPrefix *= 10;
        }
        return steps;
    }
}
