package merger;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Node root1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(4);

        root1.next = node2;
        node2.next = node3;


        Node root2 = new Node(2);
        Node node4 = new Node(6);

        root2.next = node4;

        List<Node> lists = new ArrayList<>();

        lists.add(root1);
        lists.add(root2);

        if(lists.size() == 0) {
            return;
        }
        Node head = lists.get(0);
        for(int i=1; i<lists.size(); i++){
            head = merge(head, lists.get(i));
        }


        System.out.println(JSON.toJSONString(head));

    }

    public static Node merge(Node p,Node q){
        if(p==null)
            return q;
        if(q==null)
            return p;
        Node head = null;
        Node rear = null;
        while(p!=null && q!=null){
            if(p.val < q.val){
                if(head==null){
                    head = p;
                    rear = head;
                }else{
                    rear.next = p;
                    rear = p;
                }
                p = p.next;
            }else{
                if(head==null){
                    head = q;
                    rear = head;
                }else{
                    rear.next = q;
                    rear = q;
                }
                q = q.next;
            }
        }
        if(p!=null){
            rear.next = p;
        }
        if(q!=null){
            rear.next = q;
        }
        return head;
    }

}
