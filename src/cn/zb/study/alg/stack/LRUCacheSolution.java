package cn.zb.study.alg.stack;

import java.util.*;

/**
 * @author zb
 * @date 2022-02-23
 * @description LRU 缓存
 *
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 *  - LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 *  - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 *  - void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行
 *
 * 提示：
 *  - 1 <= capacity <= 3000
 *  - 0 <= key <= 10000
 *  - 0 <= value <= 105
 *  - 最多调用 2 * 10^5 次 get 和 put
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 */
public class LRUCacheSolution {

    /**
     * 哈希表 + 双向链表
     */
    static class LRUCache3 {
        static class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
            public DLinkedNode() {}
            public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache3(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new DLinkedNode();
            tail = new DLinkedNode();

            head.next =  tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                // 如果 key 不存在，创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                ++size;

                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    /**
     * 直接使用 LinkedHashMap
     */
    static class LRUCache2 extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache2(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    /**
     * 栈 + map：超时
     */
    static class LRUCache {
        Map<Integer, Integer> map;
        Stack<Integer> stack;
        Integer capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            stack = new Stack<>();
        }

        public int get(int key) {
            if (stack.contains(key)) {
                stack.removeElement(key);
                stack.push(key);
                return map.get(key);
            }

            return -1;
        }

        public void put(int key, int value) {
            //空间没满或者空间满了但新放入的元素是已有的，直接放入，并更新顺序
            if(map.size() < capacity || (map.size() == capacity && map.containsKey(key))){
                map.put(key, value);
                if (stack.contains(key)) {
                    stack.removeElement(key);
                }
                stack.push(key);
            } else {
                int p = stack.firstElement(); //栈底元素就是最近最少使用元素
                stack.removeElement(p);
                map.remove(p);
                stack.push(key);
                map.put(key, value);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache3 lRUCache = new LRUCache3(2);
        lRUCache.put(1, 1);             // 缓存是 {1=1}
        System.out.println(lRUCache.cache.keySet());
        lRUCache.put(2, 2);             // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.cache.keySet());
        int r1 = lRUCache.get(1);       // 返回 1
        System.out.println(r1);
        lRUCache.put(3, 3);             // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.cache.keySet());
        int r2 = lRUCache.get(2);       // 返回 -1 (未找到)
        System.out.println(r2);
        lRUCache.put(4, 4);             // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.cache.keySet());
        int r3 = lRUCache.get(1);       // 返回 -1 (未找到)
        System.out.println(r3);
        int r4 = lRUCache.get(3);       // 返回 3
        System.out.println(r4);
        int r5 = lRUCache.get(4);       // 返回 4
        System.out.println(r5);
    }
}
