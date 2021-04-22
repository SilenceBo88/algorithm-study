package cn.zb.study.alg.stack.impl;

/**
 * @author zb
 * @date 2021-04-20
 * @description 链栈
 */
public class LinkStack<E> {
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

        @Override
        public String toString() {
            return "StackNode{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 栈顶节点
     */
    private StackNode<E> top;
    /**
     * 栈中节点的个数
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
        StackNode<E> newNode = new StackNode<E>(item);
        if (!isEmpty()){
            newNode.setNext(top);
        }
        top = newNode;
        ++size;
        return item;
    }

    /**
     * 出栈操作
     */
    public E pop() {
        E item = null;
        if (!isEmpty()) {
            item = top.getData();
            top = top.getNext();
            size--;
        }
        return item;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        if ((top == null) && (size == 0)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "LinkStack{" +
                "top=" + top +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        LinkStack<String> linkStack = new LinkStack<>();
        System.out.println("入栈：" + linkStack.push("a"));
        System.out.println("入栈：" + linkStack.push("b"));
        System.out.println("入栈：" + linkStack.push("c"));
        System.out.println("栈信息：" + linkStack.toString());
        System.out.println("出栈：" + linkStack.pop());
        System.out.println("出栈：" + linkStack.pop());
        System.out.println("出栈：" + linkStack.pop());
        System.out.println("出栈：" + linkStack.pop());
        System.out.println("栈信息：" + linkStack.toString());
        System.out.println("入栈：" + linkStack.push("e"));
        System.out.println("入栈：" + linkStack.push("f"));
        System.out.println("栈信息：" + linkStack.toString());
    }
}