package merger;

public class TreeLoop {

    /**
     * 一、前序遍历
     *
     * 　　访问顺序：先根节点，再左子树，最后右子树；上图的访问结果为：GDAFEMHZ。
     */

    public void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + "->");
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }


    /**
     * 二、中序遍历
     *
     * 　　访问顺序：先左子树，再根节点，最后右子树；上图的访问结果为：ADEFGHMZ。
     */
    public void inOrderTraverse(TreeNode root) {
        if (root != null) {
            inOrderTraverse(root.left);
            System.out.print(root.val + "->");
            inOrderTraverse(root.right);
        }
    }


    /**
     * 三、后序遍历
     *
     * 　　访问顺序：先左子树，再右子树，最后根节点，上图的访问结果为：AEFDHZMG。
     * @param root
     */
    public void postOrderTraverse(TreeNode root) {
        if (root != null) {
            postOrderTraverse(root.left);
            postOrderTraverse(root.right);
            System.out.print(root.val + "->");
        }
    }


}
