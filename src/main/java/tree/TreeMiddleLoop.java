package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeMiddleLoop {

    /**
     *
     * 方法一：递归
     * 思路与算法
     *
     * 首先我们需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，
     * 而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
     *
     * 定义 inorder(root) 表示当前遍历到 \textit{root}root 节点的答案，那么按照定义，
     * 我们只要递归调用 inorder(root.left) 来遍历 \textit{root}root 节点的左子树，
     * 然后将 \textit{root}root 节点的值加入答案，再递归调用inorder(root.right)
     * 来遍历 \textit{root}root 节点的右子树即可，递归终止的条件为碰到空节点。
     *
     *
     * @param args
     */
    public static void main(String[] args) {


    }


    /**
     * method 01
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal01(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res); 
        res.add(root.val);
        inorder(root.right, res);
    }


    public List<Integer> inorderTraversal02(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }




}
