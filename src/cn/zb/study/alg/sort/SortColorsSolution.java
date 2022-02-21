package cn.zb.study.alg.sort;

import java.util.Random;

/**
 * @author zb
 * @date 2022-02-21
 * @description 颜色分类
 *
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库的sort函数的情况下解决这个问题。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 * 进阶：
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 */
public class SortColorsSolution {

    /**
     * 双指针法
     * 时间复杂度：O(n)
     */
    public void sortColors2(int[] nums) {
        if (nums == null) {
            return;
        }

        int p = 0;
        int q = nums.length - 1;
        for (int i = 0; i <= q; i++) {
            if (nums[i] == 0) {
                nums[i] = nums[p];
                nums[p] = 0;
                p++;
            }
            if (nums[i] == 2) {
                nums[i] = nums[q];
                nums[q] = 2;
                --q;

                if (nums[i] != 1){
                    --i;
                }
            }
        }
    }

    /**
     * 快速排序
     * 时间复杂度：O(nlogn)
     */
    public void sortColors(int[] nums) {
        if (nums == null) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int l, int r) {
        if (l < r) {
            int pos = getPos(nums, l, r);
            sort(nums, l, pos - 1);
            sort(nums, pos + 1, r);
        }
    }

    private int getPos(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l;
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1 ; j++) {
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

    public static void main(String[] args) {
        int[] testCase = new int[]{2,0,2,1,1,0};
        new SortColorsSolution().sortColors2(testCase);
        for (int r : testCase) {
            System.out.print(r + " ");
        }
    }
}
