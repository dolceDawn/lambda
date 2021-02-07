package arithmetic;

/**
 *
 * 方法1：
 *
 * 首先创建一个以节点ID为Key的HashSet集合，用来存储曾经遍历过的节点。然后同样从头节点开始，依次遍历单链表中的每一个节点。
 * 每遍历一个新节点，都用新节点和HashSet集合中存储的节点进行比较，如果发现HashSet中存在与之相同的节点ID，则说明链表有环，
 * 如果HashSet中不存在与新节点相同的节点ID，就把这个新节点ID存入HashSet中，之后进入下一节点，继续重复刚才的操作。
 * 遍历过5、3。
 *
 *
 * 方法2：
 *
 * 首先创建两个指针p1和p2（在Java里就是两个对象引用），让它们同时指向这个链表的头节点。然后开始一个大循环，
 * 在循环体中，让指针p1每次向后移动1个节点，让指针p2每次向后移动2个节点，然后比较两个指针指向的节点是否相同。
 * 如果相同，则可以判断出链表有环，如果不同，则继续下一次循环。
 * 第1步，p1和p2都指向节点5。
 *
 */

public class LinkedCycle {

    /**
     * 判断是否有环
     *
     * @param head 链表头节点
     */
    public static boolean isCycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 链表节点
     */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws Exception {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        System.out.println(isCycle(node1));
    }


}
