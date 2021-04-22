package cn.zb.study.alg.queue.impl;

/**
 * @author zb
 * @date 2021-04-22
 * @description 链式队列
 */
public class LinkQueue<E> {
    /**
     * 节点
     */
    class QueueNode<E> {
        private E data;
        private QueueNode<E> next;

        public QueueNode(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public QueueNode<E> getNext() {
            return next;
        }

        public void setNext(QueueNode<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "QueueNode{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 队列中节点的个数
     */
    private int size;
    /**
     * 队列头节点
     */
    private QueueNode<E> head;
    /**
     * 队列尾节点
     */
    private QueueNode<E> tail;

    /**
     * 初始化队列
     */
    public LinkQueue(){
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * 入队
     */
    public boolean enqueue(E item) {
        QueueNode<E> newNode = new QueueNode<E>(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = tail.getNext();
        }
        size++;
        return true;
    }

    /**
     * 出队
     */
    public E dequeue() {
        if (isEmpty() && tail != null){
            tail = null;
            return null;
        }
        E item = head.getData();
        head = head.getNext();
        size--;
        return item;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "LinkQueue{" +
                "size=" + size +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }

    public static void main(String[] args) {
        LinkQueue<String> linkQueue = new LinkQueue<>();
        System.out.println("入队：" + linkQueue.enqueue("a"));
        System.out.println("入队：" + linkQueue.enqueue("b"));
        System.out.println("入队：" + linkQueue.enqueue("c"));
        System.out.println("队列信息：" + linkQueue.toString());
        System.out.println("出队：" + linkQueue.dequeue());
        System.out.println("出队：" + linkQueue.dequeue());
        System.out.println("出队：" + linkQueue.dequeue());
        System.out.println("出队：" + linkQueue.dequeue());
        System.out.println("队列信息：" + linkQueue.toString());
    }
}
