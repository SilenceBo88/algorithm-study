package cn.zb.study.alg.sort;

import java.util.Arrays;

/**
 * @author zb
 * @date 2022-02-22
 * @description 摆动排序 II
 *
 * 给你一个整数数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 *
 * 提示：
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 *
 * 进阶：你能用O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 *
 *
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 *
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 */
public class WiggleSort2Solution {

    /**
     * 先排序，然后通过俩个指针赋值
     * 时间复杂度：O(nlogn)，快排复杂度
     * 空间复杂度：O(n)
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int p = (nums.length + 1) / 2;
        int q = nums.length;
        int[] temp = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < temp.length; i++) {
            if (i % 2 == 0) {
                nums[i] = temp[--p];
            } else {
                nums[i] = temp[--q];
            }
        }
    }

    public static void main(String[] args) {
        int[] testCase = new int[]{1,5,1,1,6,4};
        new WiggleSort2Solution().wiggleSort(testCase);
        for (int r : testCase) {
            System.out.print(r + " ");
        }
    }
}
