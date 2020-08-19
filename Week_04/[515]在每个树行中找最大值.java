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
        Map<TreeNode, Integer> floor = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        floor.put(root, 0);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            Integer index = floor.get(curr);
            if (res.size() == index) res.add(curr.val);
            else res.set(index, Math.max(curr.val, res.get(index)));
            if (curr.left != null) {
                queue.add(curr.left);
                floor.put(curr.left, index + 1);
            }
            if (curr.right != null) {
                queue.add(curr.right);
                floor.put(curr.right, index + 1);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
