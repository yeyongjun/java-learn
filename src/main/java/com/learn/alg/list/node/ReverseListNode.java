package com.learn.alg.list.node;

/**
 * 反转链表
 *
 * @author yeyongjun
 * @since 2024/12/10 20:06
 */
public class ReverseListNode {

    /**
     * 迭代
     *
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public static ListNode reverse2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * K个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode countNode = head;
        int count = k;
        while (countNode != null && --count > 0) {
            countNode = countNode.next;
        }
        if (countNode == null) {
            return head;
        }

        ListNode last = countNode.next;
        ListNode next = head;
        ListNode newHead = head;
        while (count++ < k) {
            newHead = next;
            next = next.next;
            newHead.next = last;
            last = newHead;
        }
        head.next = reverseKGroup(next, k);
        return newHead;
    }

}
