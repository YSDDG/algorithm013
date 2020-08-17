//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 
// 👍 705 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    ///递归
    ///时间复杂度：O(N) 每个节点遍历一次
    ///空间复杂度：O(N) 递归栈及常数级的中间变量
    private boolean helper(TreeNode root, Integer lower, Integer upper) {
        ///终止条件
        if (root == null) return true;

        ///每个子树都有其上下界
        ///左子树所有节点必须小于其父节点
        ///右子树所有节点必须大于其父节点
        ///左子树中的左子节点，上界为父节点；左子树中的右子节点，下界为父节点，上界为父节点的父节点（祖父节点）
        ///右子树中的右子节点，下界为父节点；右子树中的左子节点，上届为父节点，下界为父节点的父节点（祖父节点）
        
        //上界 left
        if (upper != null && root.val >= upper) return false;
        //下界 right
        if (lower != null && root.val <= lower) return false;

        if (!helper(root.left, lower, root.val)) return false;
        if (!helper(root.right, root.val, upper)) return false;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
