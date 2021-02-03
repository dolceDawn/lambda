package listnode;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 9/29/2020 10:21
 */
public class ListNode {

    public int number;

    public ListNode next;

    public ListNode() {
    }

    public ListNode(int number) {
        this.number = number;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
