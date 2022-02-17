package cn.zb.study.alg.linked;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zb
 * @date 2022-02-16
 * @description 回文链表
 *
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 提示：链表中节点数目在范围[1, 10^5] 内；0 <= Node.val <= 9
 * 进阶：你能否用O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 输入：head = [1,2]
 * 输出：false
 */
public class PalindromeLinkedListSolution {

    /**
     * 将值复制到数组中后用双指针法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isPalindrome2(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * 快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        //找到前半部分链表的尾节点
        ListNode firstHalfEnd = endOfFirstHalf(head);
        //反转后半部分链表
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        //判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return result;
    }

    /**
     * 快慢指针找到中间节点
     */
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 反转链表
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode testCase = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        boolean result = new PalindromeLinkedListSolution().isPalindrome(testCase);
        System.out.println(result);
    }

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
}
