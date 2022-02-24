package cn.zb.study.alg.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zb
 * @date 2022-02-24
 * @description 下一个更大元素 I
 *
 * nums1中数字x的 下一个更大元素 是指x在nums2 中对应位置 右侧 的 第一个 比x大的元素。
 * 给你两个 没有重复元素 的数组nums1 和nums2 ，下标从 0 开始计数，其中nums1是nums2的子集。
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 * 返回一个长度为nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 *
 * 提示：
 * - 1 <= nums1.length <= nums2.length <= 1000
 * - 0 <= nums1[i], nums2[i] <= 104
 * - nums1 和nums2中所有整数 互不相同
 * - nums1 中的所有整数同样出现在 nums2 中
 *
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 *
 * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出：[-1,3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
 * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1
 *
 * 输入：nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出：[3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
 * - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
 */
public class NextGreaterElement1Solution {

    /**
     * 单调栈 + 哈希表
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = nums2.length - 1; i >= 0; --i) {
            int temp = nums2[i];
            while (!stack.empty() && temp >= stack.peek()) {
                stack.pop();
            }
            Integer value = stack.isEmpty() ? -1 : stack.peek();
            map.put(temp, value);
            stack.push(temp);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    /**
     * 利用栈实现
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 把nums1的数据存入栈中
        Stack<Integer> stack = new Stack<Integer>();
        for (int i : nums1) {
            stack.push(i);
        }

        int[] res = new int[nums1.length];
        int j = nums1.length - 1;
        //从后往前遍历nums2
        for (int i = nums2.length - 1; i >= 0; ) {
            //当栈不为空，且栈顶元素与nums2中顶元素相等时，开始寻找下一个更大的元素；否则i--，与前一个元素比较
            if (!stack.empty() && stack.peek() == nums2[i]) {
                int temp = stack.pop();
                int k = i + 1;
                //找出大于栈顶元素的数据在nums2中的位置
                while (k <= nums2.length - 1 && nums2[k] <= temp) {
                    k++;
                }
                //如果这个元素真的比栈顶元素大的话则返回这个元素；否则返回-1
                if (k <= nums2.length - 1 && nums2[k] > temp) {
                    res[j] = nums2[k];
                } else {
                    res[j] = -1;
                }
                j--;
                //下次循环继续从nums2的末尾开始比较
                i = nums2.length - 1;
            } else {
                i--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] testCase1 = new int[]{2,4};
        int[] testCase2 = new int[]{1,2,3,4};

        int[] result = new NextGreaterElement1Solution().nextGreaterElement2(testCase1, testCase2);
        for (int r : result) {
            System.out.print(r + " ");
        }
    }
}
