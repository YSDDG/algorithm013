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
        if (list.size() == k) {
            res.add(new LinkedList<>(list));
        }
        for (int i = first; i < n + 1; i++) {
            list.add(i);
            helper(i + 1, n, k, list);
            list.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
