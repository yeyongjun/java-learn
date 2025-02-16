package com.learn.alg.search.dfs;

import java.util.*;

/**
 * @author yeyongjun
 * @since 2025/1/13 17:37
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] array = new int[]{2,5,2,1,2}; //1,2,2,2,5
        Arrays.sort(array);
        List<List<Integer>> res = new CombinationSum().combinationSum2(array, 5);
        System.out.println(res);

//        System.out.println(firstMissingPositive2(new int[]{3,4,-1,1}));
    }

    public static int firstMissingPositive2(int[] nums) {
        int len = nums.length;
        for (int i=0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;
            }
        }
        for (int i=0; i < len; i++) {
            int n = Math.abs(nums[i]);
            if (n <= len) {
                nums[n-1] = -Math.abs(nums[n-1]);
            }
        }
        for (int i=0; i < len; i++) {
            if (nums[i] > 0) {
                return i+1;
            }
        }
        return len+1;
    }

    public int firstMissingPositive(int[] nums) {
        TreeMap<Integer, Integer> postion = new TreeMap<>();
        for (int n : nums) {
            if (n <= 0) {
                continue;
            }
            postion.put(n, 1);
        }
        int i = 1;
        while (postion.isEmpty() && postion.firstKey() == i) {
            postion.pollFirstEntry();
            i++;
        }
        return i;
    }

    /**
     * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
//        for (int i=0; i< candidates.length; i++) {
//            while (i>0 && i< candidates.length-1 && candidates[i-1] == candidates[i]) {
//                i++;
//            }
//            List<Integer> combines = new ArrayList<>();
//            combines.add(candidates[i]);
//            dfs(candidates, target-candidates[i], i+1, res, combines);
//        }
        dfs(candidates, target, 0, res, new ArrayList<>());
        return res;
    }

    private void dfs(int[] candidates, int target, int index, List<List<Integer>> res, List<Integer> combines) {
        if (target == 0) {
            res.add(new ArrayList<>(combines));
            return;

        }
        if (target < 0 || index >= candidates.length) {
            return;
        }
        for (int i=index; i< candidates.length; i++) {
            if (i>index && candidates[i-1] == candidates[i]) {
                continue;
            }
            combines.add(candidates[i]);
            dfs(candidates, target-candidates[i], i+1, res, combines);
            combines.remove(combines.size()-1);
        }
    }


    /**
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     *
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        if (target - candidates[idx] < 0) {
            return;
        }
        for (int i= idx; i< candidates.length; i++) {
            combine.add(candidates[i]);
            dfs(candidates, target - candidates[i], ans, combine, i);
            combine.remove(combine.size()-1);
        }
    }

}
