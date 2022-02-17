package cn.zb.study.alg.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zb
 * @date 2022-02-09
 * @description 三数之和
 *
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 输入：nums = []
 * 输出：[]
 * 输入：nums = [0]
 * 输出：[]
 */
public class ThreeSumSolution {

    /**
     * 双指针法
     * 时间复杂度：n*2
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        //数组长度小于3，返回空
        if (length < 3) {
            return res;
        }

        //数据排序
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            //已经排序好，所以后面不可能有三个数加和等于0，直接返回结果
            if (nums[i] > 0) {
                return res;
            }

            //对于重复元素，跳过，避免出现重复解
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int left = i + 1;
            int right = length -1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    //若和大于 0，说明 nums[right] 太大，right 左移
                    right--;
                } else if (sum < 0){
                    //若和小于 0，说明 nums[left] 太小，left 右移
                    left++;
                } else {
                    //若和等于 0，执行循环，判断左界和右界是否和下一位重复，去除重复解。并同时将 left, right 移到下一位，寻找新的解
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);

                    while (left < right && nums[left + 1] == nums[left]) left++;
                    while (left < right && nums[right - 1] == nums[right]) right--;
                    left++;
                    right--;
                }
            }
        }

        return res;
    }

    /**
     * 暴力破解，超时
     * 时间复杂度：n*3，3重for
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (length < 3) {
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    int a = nums[i];
                    int b = nums[j];
                    int c = nums[k];
                    if ((a + b + c) == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(a);
                        list.add(b);
                        list.add(c);
                        list = list.stream().sorted().collect(Collectors.toList());

                        String key = "" + list.get(0) + list.get(1) + list.get(2);
                        if (!map.containsKey(key)) {
                            res.add(list);
                            map.put(key, 1);
                        }
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> result = new ThreeSumSolution().threeSum2(nums);
        System.out.println(result);
    }
}
