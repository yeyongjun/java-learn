package com.learn.alg;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yeyongjun
 * @since 2024/12/31 16:41
 */
public class Trap {

    /**
     * 双指针
     * @param height
     * @return
     */
    public int trap4(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

    /**
     * 动态规划
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * 单调栈
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    public int trap(int[] height) {
        return trap(height, 0, height.length - 1);
    }

    private int trap(int[] height, int left, int right) {
        if ((right - left) < 2) {
            return 0;
        }
        int maxId = left + 1;
        int occupy = 0;
        for (int i = left + 1; i < right; i++) {
            if (height[i] > height[maxId]) {
                maxId = i;
            }
            occupy += height[i];
        }
        if (height[maxId] < height[left] && height[maxId] < height[right]) {
            return Math.min(height[left], height[right]) * (right - left - 1) - occupy;
        } else {
            return trap(height, left, maxId) + trap(height, maxId, right);
        }
    }

    public int trap100(int[] height) {
        int left = 1, right = height.length-2;
        int leftMax = height[0], rightMax = height[height.length-1];
        int res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] < leftMax) {
                    res += leftMax - height[left];
                }else {
                    leftMax = height[left];
                }
                left++;
            }else {
                if (height[right] < rightMax) {
                    res += rightMax - height[right];
                }else {
                    rightMax = height[right];
                }
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Trap trap = new Trap();
        System.out.println(trap.trap100(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
