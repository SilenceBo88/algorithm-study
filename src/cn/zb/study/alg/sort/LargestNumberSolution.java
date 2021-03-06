package cn.zb.study.alg.sort;

import java.util.Arrays;

/**
 * @author zb
 * @date 2022-02-22
 * @description 最大数
 *
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 *
 * 输入：nums = [10,2]
 * 输出："210"
 *
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 */
public class LargestNumberSolution {

    /**
     * 排序法：重写比较逻辑并排序（比较相邻俩数之和大小）
     * 时间复杂度：O(nlog nlogm)
     * 空间复杂度：O(logn)
     */
    public String largestNumber(int[] nums) {
        int n = nums.length;
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long x1 = Long.parseLong(x.toString() + y.toString());
            long y1 = Long.parseLong(y.toString() + x.toString());
            return (int) (y1 - x1);
        });

        if (numsArr[0] == 0) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        for (int num : numsArr) {
            res.append(num);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int[] testCase = new int[]{3,30,34,5,9};
        String result = new LargestNumberSolution().largestNumber(testCase);
        System.out.println(result);
    }
}
