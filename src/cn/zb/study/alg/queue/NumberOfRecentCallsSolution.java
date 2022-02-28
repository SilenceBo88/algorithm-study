package cn.zb.study.alg.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zb
 * @date 2022-02-26
 * @description 最近的请求次数
 *
 * 写一个RecentCounter类来计算特定时间范围内最近的请求。
 * 请你实现 RecentCounter 类：
 *  - RecentCounter() 初始化计数器，请求数为 0 。
 *  - int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 *
 * 提示：
 *  - 1 <= t <= 10^9
 *  - 保证每次对 ping 调用所使用的 t 值都 严格递增
 *  - 至多调用 ping 方法 10^4 次
 *  
 * 输入：
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * 输出：
 * [null, 1, 2, 3, 3]
 * 解释：
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
 * recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
 */
public class NumberOfRecentCallsSolution {

    /**
     * 使用队列
     */
    static class RecentCounter {
        Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.add(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        int r1 = recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
        System.out.println(r1);
        int r2 = recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
        System.out.println(r2);
        int r3 = recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
        System.out.println(r3);
        int r4 = recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
        System.out.println(r4);
    }
}
