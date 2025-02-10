package com.learn.alg.list.node;

/**
 * @author yeyongjun
 * @since 2024/12/19 10:28
 */
public class MergeListNode {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode res = new ListNode(0);
        ListNode p = res;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
            }else {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
            }
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return res.next;
    }
}
