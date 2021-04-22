package cn.zb.study.alg.stack.impl;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zb
 * @date 2021-04-20
 * @description 顺序栈
 */
public class SeqStack<E> {
    /**
     * 栈大小
     */
    private int maxSize;
    /**
     * 栈中的元素
     */
    private E[] datas;
    /**
     * 栈顶位置
     */
    private int top = -1;

    /**
     * 初始化栈
     */
    public SeqStack(Class<E> type, int size) {
        datas = (E[]) Array.newInstance(type, size);
        maxSize = size;
    }

    /**
     * 入栈操作
     */
    public boolean push(E item) {
        if (!isFull()) {
            datas[++top] = item;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 出栈操作
     */
    public E pop() {
        E item = null;
        if (!isEmpty()) {
            item = datas[top--];
        }
        return item;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
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
        if (top == maxSize - 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "SeqStack{" +
                "maxSize=" + maxSize +
                ", datas=" + Arrays.toString(datas) +
                ", top=" + top +
                '}';
    }

    public static void main(String[] args) {
        SeqStack<String> seqStack = new SeqStack<>(String.class, 3);
        System.out.println("入栈：" + seqStack.push("a"));
        System.out.println("入栈：" + seqStack.push("b"));
        System.out.println("入栈：" + seqStack.push("c"));
        System.out.println("入栈：" + seqStack.push("d"));
        System.out.println("栈信息：" + seqStack.toString());
        System.out.println("出栈：" + seqStack.pop());
        System.out.println("出栈：" + seqStack.pop());
        System.out.println("出栈：" + seqStack.pop());
        System.out.println("出栈：" + seqStack.pop());
        System.out.println("栈信息：" + seqStack.toString());
        System.out.println("入栈：" + seqStack.push("e"));
        System.out.println("入栈：" + seqStack.push("f"));
        System.out.println("栈信息：" + seqStack.toString());
    }
}
