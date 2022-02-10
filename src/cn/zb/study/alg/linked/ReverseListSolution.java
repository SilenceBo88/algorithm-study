package cn.zb.study.alg.linked;

/**
 * @author zb
 * @date 2021-04-20
 * @description 反转链表
 *
 * 反转一个单链表
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶: 迭代或递归俩种方式
 */
class ReverseListSolution {
    /**
     * 节点
     */
    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 迭代法
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; //前一节点
        ListNode current = head; //当前节点
        while (current != null){
            ListNode temp = current.next; //临时节点，记录当前节点的后一节点
            current.next = prev; //当前节点指向前一节点，进行反转
            prev = current; //当前节点成为下一节点的前一节点
            current = temp; //当前节点的后一节点成为当前节点
        }
        return prev;
    }

    /**
     * 递归法
     */
    public ListNode reverseList2(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode current) {
        //当前节点为null无法反转，直接返回
        if (current == null){
            return prev;
        }
        ListNode temp = current.next; //临时节点，记录当前节点的后一节点
        current.next = prev; //当前节点指向前一节点，进行反转
        return reverse(current, temp); //递归反转后续节点
    }

    public static void main(String[] args) {
        ListNode testCase = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode result = new ReverseListSolution().reverseList2(testCase);
        while (result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
