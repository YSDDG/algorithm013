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
// 👍 1203 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        helper(0, 0, n, "");
        return res;
    }
    /**
     * 2*n个格子依次放入左括号和右括号
     * l 左括号的数量
     * r 右括号的数量
     * s 结果
     */
    private void helper(int l, int r, int n, String s) {
        ///左右括号全部放入格子中，返回当前字符串
        if (l == n && r == n) {
            res.add(s);
            return;
        }
        ///左括号小于n，直接放
        if (l < n) {
            helper(l + 1, r, n, s + "(");
        }
        ///右括号小于左括号，直接放
        if (r < l) helper(l, r + 1, n, s += ")");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
