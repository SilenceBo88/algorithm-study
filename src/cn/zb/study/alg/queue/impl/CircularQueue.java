package cn.zb.study.alg.queue.impl;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zb
 * @date 2021-04-22
 * @description 循环队列
 */
public class CircularQueue<E> {
    /**
     * 队列大小
     */
    private int maxSize;
    /**
     * 队列数据
     */
    private E[] datas;
    /**
     * 队列头下标
     */
    private int head = 0;
    /**
     * 队列尾下标
     */
    private int tail = 0;

    /**
     * 初始化队列
     */
    public CircularQueue(Class<E> type, int size) {
        datas = (E[]) Array.newInstance(type, size);
        maxSize = size;
    }

    /**
     * 入队
     */
    public boolean enqueue(E item) {
        if (isFull()) {
            return false;
        }
        datas[tail] = item;
        tail = (tail + 1) % maxSize;
        return true;
    }

    /**
     * 出队
     */
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E item = datas[head];
        head = (head + 1) % maxSize;
        return item;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        if (head == tail) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断队列是否为满
     */
    public boolean isFull() {
        if ((tail + 1) % maxSize == head) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "SeqQueue{" +
                "maxSize=" + maxSize +
                ", datas=" + Arrays.toString(datas) +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(String.class, 4);
        System.out.println("入队：" + queue.enqueue("a"));
        System.out.println("入队：" + queue.enqueue("b"));
        System.out.println("入队：" + queue.enqueue("c"));
        System.out.println("入队：" + queue.enqueue("d"));
        System.out.println("队列信息：" + queue.toString());
        System.out.println("出队：" + queue.dequeue());
        System.out.println("出队：" + queue.dequeue());
        System.out.println("队列信息：" + queue.toString());
        System.out.println("入队：" + queue.enqueue("e"));
        System.out.println("入队：" + queue.enqueue("f"));
        System.out.println("入队：" + queue.enqueue("g"));
        System.out.println("队列信息：" + queue.toString());
    }
}
