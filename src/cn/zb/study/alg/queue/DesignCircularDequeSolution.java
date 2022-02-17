package cn.zb.study.alg.queue;

/**
 * @author zb
 * @date 2022-02-17
 * @description 设计循环双端队列
 *
 * 设计实现双端队列。
 * 实现 MyCircularDeque 类:
 * MyCircularDeque(int k)：构造函数,双端队列最大为 k。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true，否则返回 false。
 * boolean insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true，否则返回 false。
 * boolean deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true，否则返回 false。
 * boolean deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true，否则返回 false。
 * int getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * int getRear()：获得双端队列的最后一个元素。如果双端队列为空，返回 -1。
 * boolean isEmpty()：若双端队列为空，则返回true，否则返回 false。
 * boolean isFull()：若双端队列满了，则返回true，否则返回 false。
 *
 * 提示：
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * insertFront,insertLast,deleteFront,deleteLast,getFront,getRear,isEmpty,isFull 调用次数不大于2000次
 *
 *
 * 输入
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * 输出
 * [null, true, true, true, false, 2, true, true, true, 4]
 * 解释
 * MyCircularDeque circularDeque = new MycircularDeque(3);  // 设置容量大小为3
 * circularDeque.insertLast(1);			                    // 返回 true
 * circularDeque.insertLast(2);			                    // 返回 true
 * circularDeque.insertFront(3);			                // 返回 true
 * circularDeque.insertFront(4);			                // 已经满了，返回 false
 * circularDeque.getRear();  				                // 返回 2
 * circularDeque.isFull();				                    // 返回 true
 * circularDeque.deleteLast();			                    // 返回 true
 * circularDeque.insertFront(4);			                // 返回 true
 * circularDeque.getFront();				                // 返回 4
 *
 */
public class DesignCircularDequeSolution {

    /**
     * 使用数组记录队列值，标记数组中元素个数即可
     * （避免考虑循环队列，思路跑偏）
     */
    static class MyCircularDeque {

        private int[] datas;

        private int size;

        public MyCircularDeque(int k) {
            datas = new int[k];
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            for (int i = size-1; i >= 0 ; i--) {
                datas[i+1] = datas [i];
            }
            datas[0] = value;
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            datas[size] = value;
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            datas[0] = 0;
            for (int i = 0; i < size-1; i++) {
                datas[i] = datas[i+1];
            }
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            datas[size-1] = 0;
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return datas[0];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return datas[size - 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == datas.length;
        }
    }

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3);
        System.out.println(circularDeque.insertLast(1));
        System.out.println(circularDeque.insertLast(2));
        System.out.println(circularDeque.insertFront(3));
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getRear());
        System.out.println(circularDeque.isFull());
        System.out.println(circularDeque.deleteLast());
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getFront());
    }
}
