//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 99 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        List<Node> roots = new ArrayList<>();
        roots.add(root);
        helper(roots, res);
        return res;
    }
    ///下一层为空时，停止递归
    ///遍历当前层的结果，放入列表这种
    ///合并各个节点下一层的子节点，不为空，继续递归
    private void helper(List<Node> nodes, List<List<Integer>> list) {
        List<Integer> res = new ArrayList<>();
        List<Node> nextLevel = new ArrayList<>();
        for (Node node : nodes) {
            res.add(node.val);
            if (node.children != null)
                nextLevel.addAll(node.children);
        }
        list.add(res);
        if (nextLevel.size() > 0) {
            helper(nextLevel, list);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
