package cn.zb.study.alg.array;

/**
 * @author zb
 * @date 2022-02-25
 * @description 最大子数组和
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和；子数组 是数组中的一个连续部分。
 * 提示：
 *  - 1 <= nums.length <= 105
 *  - -104 <= nums[i] <= 104
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 输入：nums = [1]
 * 输出：1
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 */
public class MaximumSubArray {

    /**
     * 动态规划
     * 时间复杂度：O(n)
     */
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int pre = 0;
        int maxSum = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxSum = Math.max(pre, maxSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] testCase = new int[]{5,4,-1,7,8};
        int result = new MaximumSubArray().maxSubArray(testCase);
        System.out.println(result);
    }
}
