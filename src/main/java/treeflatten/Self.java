package treeflatten;

public class Self {

    public static void flattenLoop(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.getLeft() != null) {
                TreeNode p = cur.getLeft();
                while (p.getRight() != null) {
                    p = p.getRight();
                }
                p.setRight(cur.getLeft());
                cur.setLeft(null);
                cur.setRight(cur.getLeft());
            }
            cur = cur.getRight();
        }

    }


}
