package cn.zb.study.alg.stack;

import java.util.*;

/**
 * @author zb
 * @date 2022-02-17
 * @description 最小栈
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 提示：pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop()—— 删除栈顶的元素。
 * top()—— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 */
public class MinStackSolution {

    /**
     * 辅助栈法
     * 时间复杂度：O(1)
     * 空间复杂度：O(n)
     */
    static class MinStack2 {
        Deque<Integer> xStack;
        Deque<Integer> minStack;

        public MinStack2() {
            xStack = new LinkedList<Integer>();
            minStack = new LinkedList<Integer>();
            minStack.push(Integer.MAX_VALUE);
        }

        /**
         * 入栈操作
         */
        public void push(int val) {
            xStack.push(val);
            minStack.push(Math.min(minStack.peek(), val));
        }

        /**
         * 出栈操作
         */
        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        /**
         * 栈顶元素
         */
        public int top() {
            return xStack.peek();
        }

        /**
         * 栈中最小元素
         */
        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * 列表存储法
     */
    static class MinStack {
        /**
         * 栈中的元素
         */
        private List<Integer> datas = new ArrayList<>();
        /**
         * 栈顶位置
         */
        private int top = -1;

        public MinStack() {
        }

        /**
         * 入栈操作
         */
        public void push(int val) {
            top++;
            datas.add(val);
        }

        /**
         * 出栈操作
         */
        public void pop() {
            if (!datas.isEmpty()) {
                datas.remove(top);
                top--;
            }
        }

        /**
         * 栈顶元素
         */
        public int top() {
            return datas.get(top);
        }

        /**
         * 栈中最小元素
         */
        public int getMin() {
            return Collections.min(datas);
        }
    }

    public static void main(String[] args) {
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min1 = minStack.getMin();
        System.out.println(min1);
        minStack.pop();
        int top1 = minStack.top();
        System.out.println(top1);
        int min2 = minStack.getMin();
        System.out.println(min2);
    }
}
