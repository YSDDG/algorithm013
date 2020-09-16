//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 示例: 
//
// 输入:
//[
// [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
// 
// Related Topics 数组 动态规划 
// 👍 649 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {
        /**
         * p(i,j) = min(p(i+1,j),p(i)(j+1))+a[i][j];
         * dp[i][j] = min(dp[i+1][j],dp[i][j+1])+a[i][j]
         */
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = grid[i][j];
                    continue;
                }
                if (i == m - 1) {
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                    continue;
                }
                if (j == n - 1) {
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                    continue;
                }
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
