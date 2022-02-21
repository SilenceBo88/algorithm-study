package cn.zb.study.alg.sort;

import java.util.Random;

/**
 * @author zb
 * @date 2022-02-21
 * @description 排序数组
 *
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * 提示：
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 *
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 */
public class SortAnArraySolution {

    /**
     * 快速排序
     * 时间复杂度：O(nlogn)
     */
    public int[] sortArray4(int[] nums) {
        randomizedQuicksort(nums, 0, nums.length - 1);
        return nums;
    }

    private void randomizedQuicksort(int[] nums, int l, int r) {
        if (l < r) {
            int pos = randomizedPartition(nums, l, r);
            randomizedQuicksort(nums, l, pos - 1);
            randomizedQuicksort(nums, pos + 1, r);
        }
    }

    private int randomizedPartition(int[] nums, int l, int r) {
        //随机选一个作为我们的主元
        int i = new Random().nextInt(r - l + 1) + l;
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1 ; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 选择排序
     * 时间复杂度：O(n^2)
     */
    public int[] sortArray3(int[] nums) {
        if (nums == null) {
            return null;
        }

        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }

            if (i != min){
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }

        return nums;
    }

    /**
     * 插入排序
     * 时间复杂度：O(n^2)
     */
    public int[] sortArray2(int[] nums) {
        if (nums == null) {
            return null;
        }

        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (nums[j] > value) {
                    nums[j+1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j+1] = value;
        }

        return nums;
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n^2)
     */
    public int[] sortArray(int[] nums) {
        if (nums == null) {
            return null;
        }
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] testCase = new int[]{5,1,1,2,0,0};
        int[] result = new SortAnArraySolution().sortArray4(testCase);
        for (int r : result) {
            System.out.print(r + " ");
        }
    }
}
