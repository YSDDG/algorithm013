//您需要在二叉树的每一行中找到最大的值。 
//
// 示例： 
//
// 
//输入: 
//
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
//
//输出: [1, 3, 9]
// 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 86 👎 0


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

    List<Integer> res = new ArrayList<>();

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 0));
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (res.size() == current.level)
                res.add(current.node.val);
            else
                res.set(current.level, res.get(current.level) == null ? current.node.val : Math.max(res.get(current.level), current.node.val));
            if (current.node.left != null) queue.add(new Node(current.node.left, current.level + 1));
            if (current.node.right != null) queue.add(new Node(current.node.right, current.level + 1));
        }
        return res;
    }

    static class Node {
        Integer level;
        TreeNode node;

        public Node(TreeNode node, Integer level) {
            this.node = node;
            this.level = level;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
