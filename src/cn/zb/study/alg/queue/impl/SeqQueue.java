package cn.zb.study.alg.queue.impl;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zb
 * @date 2021-04-22
 * @description 顺序队列
 */
public class SeqQueue<E> {
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
    public SeqQueue(Class<E> type, int size) {
        datas = (E[]) Array.newInstance(type, size);
        maxSize = size;
    }

    /**
     * 入队
     */
    public boolean enqueue(E item) {
        if (isTail()) {
            return false;
        }
        datas[tail] = item;
        tail++;
        return true;
    }

    /**
     * 入队（带数据搬移）
     */
    public boolean enqueueWithMove(E item) {
        if (isTail()) {
            if (isFull()) {
                return false;
            }
            //数据搬移
            for (int i = head; i < tail; i++){
                datas[i-head] = datas[i];
            }
            tail = tail - head;
            head = 0;
        }
        datas[tail] = item;
        tail++;
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
        head++;
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
        if (tail == maxSize && head == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断队列尾下标是否到末尾
     */
    public boolean isTail() {
        if (tail == maxSize) {
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
        SeqQueue seqQueue = new SeqQueue(String.class, 3);
        System.out.println("入队：" + seqQueue.enqueue("a"));
        System.out.println("入队：" + seqQueue.enqueue("b"));
        System.out.println("入队：" + seqQueue.enqueue("c"));
        System.out.println("入队：" + seqQueue.enqueue("d"));
        System.out.println("队列信息：" + seqQueue.toString());
        System.out.println("出队：" + seqQueue.dequeue());
        System.out.println("出队：" + seqQueue.dequeue());
        System.out.println("出队：" + seqQueue.dequeue());
        System.out.println("出队：" + seqQueue.dequeue());
        System.out.println("队列信息：" + seqQueue.toString());

        System.out.println("---------------------------------------------------");

        SeqQueue seqQueueWithMove = new SeqQueue(String.class, 3);
        System.out.println("入队：" + seqQueueWithMove.enqueueWithMove("a"));
        System.out.println("入队：" + seqQueueWithMove.enqueueWithMove("b"));
        System.out.println("入队：" + seqQueueWithMove.enqueueWithMove("c"));
        System.out.println("入队：" + seqQueueWithMove.enqueueWithMove("d"));
        System.out.println("队列信息：" + seqQueueWithMove.toString());
        System.out.println("出队：" + seqQueueWithMove.dequeue());
        System.out.println("队列信息：" + seqQueueWithMove.toString());
        System.out.println("入队：" + seqQueueWithMove.enqueueWithMove("e"));
        System.out.println("入队：" + seqQueueWithMove.enqueueWithMove("f"));
        System.out.println("队列信息：" + seqQueueWithMove.toString());
    }
}
