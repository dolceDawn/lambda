package listnode;

import com.alibaba.fastjson.JSON;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 9/29/2020 10:22
 */
public class Solution {


    public static void main(String[] args) {

        ListNode head = new ListNode();

        head.setNumber(1);

        ListNode n1 = new ListNode();
        n1.setNumber(2);
        head.setNext(n1);

        ListNode n2 = new ListNode();
        n2.setNumber(3);
        n1.setNext(n2);

        ListNode n3 = new ListNode();
        n3.setNumber(4);
        n2.setNext(n3);

        ListNode n4 = new ListNode();
        n4.setNumber(5);
        n3.setNext(n4);

        ListNode result = reverseList(head);

        System.out.println(JSON.toJSONString(result));

    }

    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static ListNode reverseListInterator(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode nxt = curr.next;
            // 翻转箭头
            curr.next = prev;
            //三人行
            prev = curr;
            //三人行
            curr = nxt;
        }

        return prev;
    }

}
