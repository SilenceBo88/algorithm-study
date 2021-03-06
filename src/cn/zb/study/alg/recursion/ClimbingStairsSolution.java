package cn.zb.study.alg.recursion;

/**
 * @author zb
 * @date 2022-02-10
 * @description 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 提示：1 <= n <= 45
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 */
public class ClimbingStairsSolution {

    /**
     * 动态规划
     */
    public int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int res = 0;
        int pre = 2;
        int prePre = 1;
        for (int i = 3; i <= n; i++) {
            res = pre + prePre;
            prePre = pre;
            pre = res;
        }

        return res;
    }

    /**
     * 递归法
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return climbStairs(n-1) + climbStairs(n-2);
    }

    public static void main(String[] args) {
        int result = new ClimbingStairsSolution().climbStairs2(45);
        System.out.println(result);
    }
}
