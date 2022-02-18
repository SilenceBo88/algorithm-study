package cn.zb.study.alg.recursion;

/**
 * @author zb
 * @date 2022-02-18
 * @description 两两交换链表中的节点
 *
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 提示：链表中节点的数目在范围 [0, 100] 内；0 <= Node.val <= 100。
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 输入：head = []
 * 输出：[]
 *
 * 输入：head = [1]
 * 输出：[1]
 */
public class SwapNodesInPairsSolution {

    /**
     * 迭代法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }

    /**
     * 递归法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp ;
    }

    public static void main(String[] args) {
        ListNode testCase = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode result = new SwapNodesInPairsSolution().swapPairs2(testCase);
        while (result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
