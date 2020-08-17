//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最小深度 2. 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 315 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    ///递归
    ///时间复杂度：O(N) 每个节点遍历一次
    ///空间复杂度：O(height) 
    public int minDepth(TreeNode root) {
        ///终止条件
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        ///左子节点或者右子节点为空，此处要注意    
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        ///下探
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
