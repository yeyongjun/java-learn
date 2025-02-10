import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yeyongjun
 * @since 2024/12/30 14:16
 */
public class AlgLocalTest {

    public static int storeWater(int[] bucket, int[] vat) {
        Map<Integer, Integer> stepAndUpgradeMap = new HashMap<>();
        for (int i=0; i< bucket.length; i++) {
            if (vat[i] == 0) {
                continue;
            }
            int upCount = 0;
            if (bucket[i] == 0 && vat[i] !=0) {
                upCount++;
                bucket[i] = 1;
            }

            if (bucket[i] >= vat[i]) {
                compute(stepAndUpgradeMap, 1, upCount);
                continue;
            }
            int bucketSize = bucket[i];
            int vatSize = vat[i];

            int step2 = countStep(bucketSize, vatSize);
            compute(stepAndUpgradeMap, step2, upCount);
            int upgradeAndStep2 = countStep(bucketSize + 1, vatSize);
            while (upgradeAndStep2 + 1 <= step2) {
                bucketSize++;
                upCount++;
                compute(stepAndUpgradeMap, upgradeAndStep2, upCount);

                step2 = upgradeAndStep2;
                upgradeAndStep2 = countStep(bucketSize + 1, vatSize);
            }

        }
        if (stepAndUpgradeMap.isEmpty()) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : stepAndUpgradeMap.entrySet()) {
            res = Math.min(res, entry.getKey()+ entry.getValue());
        }
        return res;
    }

    private static void compute(Map<Integer, Integer> stepAndUpgradeMap, Integer key, Integer value) {
        if (stepAndUpgradeMap.containsKey(key)) {
            stepAndUpgradeMap.put(key, stepAndUpgradeMap.get(key) + value);
        }else {
            stepAndUpgradeMap.put(key, value);
        }
    }

    private static int countStep(int bucketSize, int vatSize) {
        return vatSize / bucketSize + (vatSize % bucketSize > 0 ? 1: 0);
    }

    public static void main(String[] args) {
        storeWater(new int[]{16,29,42,70,42,9}, new int[]{89,44,50,90,94,91});
    }


    /**
     * 接雨水
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height.length <=2) {
            return 0;
        }
        int len = height.length;
        int res = 0;
        for (int i = 1; i< len -1;i++) {
            int left = i-1;
            int right = i+1;
            if (height[left] > height[i] && height[right] >= height[i]) {
                int leftOccupy = 0;
                while (left>0 && height[left-1] >= height[left]) {
                    leftOccupy += height[left];
                    left--;
                }
                int rightOccupy = 0;
                while (right < len-2 && height[right+1] >= height[right]) {
                    rightOccupy += height[right];
                    right++;
                }
                res += Math.min(height[left], height[right]) * (right-left -1) - leftOccupy - rightOccupy - height[i];
            }
        }
        return res;
    }

    public static int jump2(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        Map<Integer, Integer> stepMap = new HashMap<>();
        stepMap.put(0, 0);
        for (int i = 0; i < nums.length; i++) {
            fillMap(nums, i, stepMap);
        }
        return stepMap.get(nums.length - 1);
    }

    public static void fillMap(int[] nums, int index, Map<Integer, Integer> stepMap) {
        int n = nums[index];
        if (n == 0) {
            return;
        }
        System.out.println(index + ":" + stepMap.toString());
        int currentStep = stepMap.get(index);
        for (int i = index + 1; i <= index + n && i < nums.length; i++) {
            if (stepMap.containsKey(i)) {
                stepMap.put(i, Math.min(currentStep + 1, stepMap.get(i)));
            } else {
                stepMap.put(i, currentStep + 1);
            }
        }
    }
}
