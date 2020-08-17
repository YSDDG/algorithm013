//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 327 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        helper(1, n, k, new LinkedList<>());
        return res;
    }

    private void helper(int first, int n, int k, LinkedList<Integer> list) {
        ///终止条件
        if (list.size() == k) {
            res.add(new LinkedList<>(list));
        }
        ///选择
        for (int i = first; i < n + 1; i++) {
            ///选择当前元素
            list.add(i);
            ///下一个
            helper(i + 1, n, k, list);
            ///回溯
            list.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
