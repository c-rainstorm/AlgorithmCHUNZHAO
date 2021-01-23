package s0000;

import me.rainstorm.ds.ListNode;

public class N0021MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sen = new ListNode(0);
        ListNode cur = sen;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            }
        }

        if (l1 == null) cur.next = l2;
        if (l2 == null) cur.next = l1;

        return sen.next;
    }
}
