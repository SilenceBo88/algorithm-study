package cn.zb.study.alg.array;

import java.util.Arrays;

/**
 * @author zb
 * @date 2022-02-16
 * @description 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊n/2⌋的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * 输入：[3,2,3]
 * 输出：3
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 */
public class MajorityElementSolution {

    /**
     * 投票算法
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     */
    public int majorityElement3(int[] nums) {
        int count = 0;
        int majority = 0;

        for (int num : nums) {
            if (count == 0){
                majority = num;
            }

            count = count + (num == majority ? 1 : -1);
        }

        return majority;
    }

    /**
     * 排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 排序，未考虑到 n/2 的实现
     */
    public int majorityElement(int[] nums) {
        //排序，使数组有序
        Arrays.sort(nums);

        //出现次数最多的数的位置
        int index = 0;
        //出现次数最多的数的次数
        int preCount = 0;
        for (int i = 1; i < nums.length; i++) {
            //临时个数统计
            int curCount = 0;
            //当前一个数小于后一个数 或 循环到最后一个数时，计算数重复出现的次数
            if (nums[i-1] < nums[i] || i+1 == nums.length){
                curCount = i - preCount;
                //当循环到最后一个数时，数重复出现的次数+1
                if (i+1 == nums.length) {
                    curCount = curCount + 1;
                }
                //当前数的出现次数大于前一数时，记录位置，并把当前数的统计次数设置为前一数的统计次数
                if (curCount > preCount) {
                    preCount = curCount;
                    index = i-1;
                }
            }
        }

        return nums[index];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,1,1,2,2};
        int result = new MajorityElementSolution().majorityElement3(nums);
        System.out.println(result);
    }
}
