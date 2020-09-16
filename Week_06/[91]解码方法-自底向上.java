//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划 
// 👍 499 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        /*
         * 重复字问题
         * 从后向前
         * dp状态 p(n) = p(n-1)+p(n-2) || p(n-1)
         * dp方程 dp[i] =
         */

        if (s.startsWith("0")) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n - 1] = dp[n] = 1;
        if (s.endsWith("0")) {
            dp[n - 1] = 0;
        }
        for (int i = n - 2; i >= 0; i--) {
            char ci = s.charAt(i);
            char cin = s.charAt(i + 1);
            if (cin == '0' && ci != '1' && ci != '2') {
                return 0;
            }
            if (cin == '0') {
                dp[i] = dp[i + 2];
            } else if ((ci - '0') * 10 + (cin - '0') > 26) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = dp[i + 1] + dp[i + 2];
            }
        }
        return dp[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
