package arithmetic;

public class RedBlackTree {

    private final int R=0;
    private final int B=1;
    private Node root=null;  //红黑树根节点
    class Node{
        int data;      //存的具体数字
        int color=R;
        Node left;
        Node right;
        Node parent;
        public Node(int data){
            this.data=data;
        }
    }

    //插入
    public void insert(Node root, int data){
        if(root.data<data){
            if(root.right==null){
                root.right=new Node(data);
            }else{
                insert(root.right,data);
            }
        }else{
            if(root.left==null){
                root.left=new Node(data);
            }else{
                insert(root.left,data);
            }
        }
    }

    //左旋
    public void leftRotate(Node node){
        if(node.parent==null){          //表示根节点
            //确定ES
            Node E=root;
            Node S=E.right;

            //移S左子数
            E.right=S.left;
            S.left.parent=E;

            //修改E的指针
            E.parent=S;

            //修改S的指针
            S.parent=null;
        }
    }

}
