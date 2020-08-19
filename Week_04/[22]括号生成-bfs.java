//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1248 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        bfs(0, 0, 0, n, "");
        return res;
    }

    private void bfs(int level, int l, int r, int n, String s) {
        if (level == 2 * n) res.add(s);
        if (l < n) bfs(level + 1, l + 1, r, n, s + "(");
        if (r < l) bfs(level + 1, l, r + 1, n, s + ")");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
