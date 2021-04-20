package cn.zb.study.alg.stack.impl;

import java.lang.reflect.Array;

/**
 * @author zb
 * @date 2021-04-20
 * @description 顺序栈
 */
public class SeqStack<E> {
    /**
     * 栈大小
     */
    private int maxsize;
    /**
     * 栈中的元素
     */
    private E[] data;
    /**
     * 栈顶位置
     */
    private int top;

    /**
     * 初始化栈
     */
    public SeqStack(Class<E> type, int size) {
        data = (E[]) Array.newInstance(type, size);
        maxsize = size;
        top = -1;
    }

    /**
     * 入栈操作
     */
    public E push(E item) {
        if (!isFull()) {
            data[++top] = item;
            return item;
        } else {
            return null;
        }
    }

    /**
     * 出栈操作
     */
    public E pop() {
        E item = null;
        if (!empty()) {
            item = data[top--];
        }
        return item;
    }

    /**
     * 判断栈是否为空
     */
    public boolean empty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断栈是否为满
     */
    public boolean isFull() {
        if (top == maxsize - 1) {
            return true;
        } else {
            return false;
        }
    }
}
