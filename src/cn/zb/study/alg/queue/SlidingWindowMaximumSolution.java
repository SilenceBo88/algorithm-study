package cn.zb.study.alg.queue;

import java.util.*;

/**
 * @author zb
 * @date 2022-02-11
 * @description 滑动窗口最大值
 *
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。返回 滑动窗口中的最大值 。
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 */
public class SlidingWindowMaximumSolution {

    /**
     * 单调队列
     * 时间复杂度，O(n)
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        //使用一个队列存储所有还没有被移除的下标，这些下标按照从小到大的顺序被存储
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; i++) {
            //不断地将新的元素与队尾的元素相比较
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int j = k; j < n; j++) {
            //不断地将新的元素与队尾的元素相比较
            while (!deque.isEmpty() && nums[j] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(j);
            //不断从队首弹出元素
            while (deque.peekFirst() <= j - k) {
                deque.pollFirst();
            }
            //队列中下标对应的元素是严格单调递减的，因此此时队首下标对应的元素就是滑动窗口中的最大值
            ans[j - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    /**
     * 优先队列
     * 时间复杂度，O(nlogn)
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        //优先队列（堆），其中的大根堆可以帮助我们实时维护一系列元素中的最大值
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });

        //将数组 nums 的前 k 个元素放入优先队列中
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int j = k; j < n; j++) {
            pq.offer(new int[]{nums[j], j});
            //不断地移除堆顶的元素，直到其确实出现在滑动窗口中
            while (pq.peek()[1] <= j - k) {
                pq.poll();
            }
            //堆顶元素就是滑动窗口中的最大值
            ans[j - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    /**
     * 暴力破解，超时
     * 时间复杂度，O(nk)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (!(1 <= k && k <= nums.length)) {
            return new int[0];
        }

        int[] res = new int[nums.length - (k-1)];
        Queue<Integer> queue = new ArrayDeque<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() < k) {
                queue.offer(nums[i]);
            } else if (queue.size() == k) {
                res[i - (k-1) -1] = Collections.max(queue);

                queue.poll();
                queue.offer(nums[i]);
            }
        }

        res[res.length -1] = Collections.max(queue);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] result = new SlidingWindowMaximumSolution().maxSlidingWindow3(nums, k);
        for (int r : result) {
            System.out.print(r + " ");
        }
    }
}
