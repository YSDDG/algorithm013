//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。 
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 例如，给定三角形： 
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。 
//
// 
//
// 说明： 
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。 
// Related Topics 数组 动态规划 
// 👍 592 👎 0


import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        List<List<Integer>> dp = triangle;
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j < dp.get(i).size(); j++) {
                dp.get(i).set(j, dp.get(i).get(j) + Math.min(dp.get(i + 1).get(j), dp.get(i + 1).get(j + 1)));
            }
        }
        return dp.get(0).get(0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
