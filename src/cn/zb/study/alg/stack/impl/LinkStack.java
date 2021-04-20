package cn.zb.study.alg.stack.impl;

/**
 * @author zb
 * @date 2021-04-20
 * @description 链栈
 */
public class LinkStack<E> {
    /**
     * 栈顶节点
     */
    private StackNode<E> top;
    /**
     * 栈中结点的个数
     */
    private int size;

    /**
     * 初始化栈
     */
    public LinkStack() {
        top = null;
        size = 0;
    }

    /**
     * 入栈操作
     */
    public E push(E item) {
        StackNode<E> newnode = new StackNode<E>(item);
        if (!empty()){
            newnode.setNext(top);
        }
        top = newnode;
        ++size;
        return item;
    }

    /**
     * 出栈操作
     */
    public E pop() {
        E item = null;
        if (!empty()) {
            item = top.getData();
            top = top.getNext();
            size--;
        }
        return item;
    }

    /**
     * 判断栈是否为空
     */
    public boolean empty() {
        if ((top == null) && (size == 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 节点
     */
    class StackNode<E> {
        private E data;
        private StackNode<E> next;

        public StackNode(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public StackNode<E> getNext() {
            return next;
        }

        public void setNext(StackNode<E> next) {
            this.next = next;
        }
    }
}