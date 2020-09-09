//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 例如，上图是一个7 x 3 的网格。有多少可能的路径？ 
//
// 
//
// 示例 1: 
//
// 输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
// 
//
// 示例 2: 
//
// 输入: m = 7, n = 3
//输出: 28 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 10 ^ 9 
// 
// Related Topics 数组 动态规划 
// 👍 672 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Map<String, Integer> cache = new HashMap<>();

    public int uniquePaths(int m, int n) {
        if (m < 1 || n < 1) return 0;
        if (m == 1 || n == 1) return 1;
        Integer u1, u2;
        if (cache.get((m - 1) + "," + n) == null) {
            u1 = uniquePaths(m - 1, n);
            cache.put((m - 1) + "," + n, u1);
        } else {
            u1 = cache.get((m - 1) + "," + n);
        }
        if (cache.get(m + "," + (n - 1)) == null) {
            u2 = uniquePaths(m, n - 1);
            cache.put(m + "," + (n - 1), u2);
        } else {
            u2 = cache.get(m + "," + (n - 1));
        }
        return u1 + u2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
