//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 612 👎 0


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
     public TreeNode buildTree(int[] preorder, int[] inorder) {
        ///终止条件
        if (preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length == 1) {
            return root;
        }
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            idxMap.put(inorder[i], i);
        }

        ///当前逻辑
        Integer rootIdx = idxMap.get(preorder[0]);
        System.out.println(rootIdx);
        int leftLength = rootIdx;
        if (leftLength > 0) {
            int[] leftPreOrder = new int[leftLength];
            System.arraycopy(preorder, 1, leftPreOrder, 0, leftLength);
            int[] leftInOrder = new int[leftLength];
            System.arraycopy(inorder, 0, leftInOrder, 0, leftLength);
            //下一层
            root.left = buildTree(leftPreOrder, leftInOrder);
        }
        int rightLength = inorder.length - 1 - rootIdx;
        if (rightLength > 0) {
            int[] rightPreorder = new int[rightLength];
            System.arraycopy(preorder, leftLength + 1, rightPreorder, 0, rightLength);
            int[] rightInorder = new int[rightLength];
            System.arraycopy(inorder, leftLength + 1, rightInorder, 0, rightLength);
            ///下一层
            root.right = buildTree(rightPreorder, rightInorder);
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
