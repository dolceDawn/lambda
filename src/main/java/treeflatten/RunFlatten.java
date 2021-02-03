package treeflatten;

public class RunFlatten {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.setLeft(n2);
        n1.setRight(n5);

        n2.setLeft(n3);
        n2.setRight(n4);

        n5.setRight(n6);
        // flattenRecursion(n1);
        flattenLoop(n1);

        System.out.println(n1);
    }


    public static void flattenRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        /*使用DFS思路找到最左子节点*/
        if (root.getLeft() != null) {
            flattenRecursion(root.getLeft());
        }
        if (root.getRight() != null) {
            flattenRecursion(root.getRight());
        }
        /*把其父节点和右子节点断开，将原左子结点连上父节点的右子节点上*/
        TreeNode tmp = root.getRight();
        root.setRight(root.getLeft());
        root.setLeft(null);
        /*然后再把原右子节点连到新右子节点的右子节点上*/
        while (root.getRight() != null) {
            root = root.getRight();
        }
        root.setRight(tmp);
    }


    public static void flattenLoop(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            //遍历节点 如果左节点不为空的话
            if (cur.getLeft() != null) {
                //找到左子树的最右节点 目的是把原右子树连接到该节点的右节点上
                TreeNode p = cur.getLeft();
                while (p.getRight() != null) {
                    p = p.getRight();
                }
                //断开当前节点和右子树 把左子树连接到当前节点的右节点 把原右子树连接到原左子树的最右节点的右子树上
                p.setRight(cur.getRight());
                cur.setRight(cur.getLeft());
                cur.setLeft(null);
            }
            //当前节点为右子树的根节点 即原左子树的根节点
            cur = cur.getRight();
        }
    }
}
