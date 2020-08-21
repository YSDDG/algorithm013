//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 715 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        if (grid == null || grid.length == 0) return res;
        int l = grid.length;
        int w = grid[0].length;
        for (int k = 0; k < l; k++) {
            for (int m = 0; m < w; m++) {
                if (grid[k][m] == '1') {
                    res++;
                    breakIsland(k, m, l, w, grid);
                }
            }
        }
        return res;
    }

    private void breakIsland(int k, int m, int l, int w, char[][] grid) {
        grid[k][m] = '0';
        if (m < w - 1 && grid[k][m + 1] == '1')
            breakIsland(k, m + 1, l, w, grid);
        if (m > 0 && grid[k][m - 1] == '1')
            breakIsland(k, m - 1, l, w, grid);
        if (k < l - 1 && grid[k + 1][m] == '1')
            breakIsland(k + 1, m, l, w, grid);
        if (k > 0 && grid[k - 1][m] == '1')
            breakIsland(k - 1, m, l, w, grid);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
