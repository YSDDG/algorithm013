//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 836 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        helper(nums, 0, new HashSet<>(), new LinkedList<Integer>());
        return res;
    }

    /**
     * 时间复杂度：O(n*n!)（helper时间复杂度为n!,helper被调用n次）
     * 空间复杂度：中间列表以及递归栈（O（N））
     */
    private void helper(int[] nums, int index, Set<Integer> seen, LinkedList<Integer> list) {
        ///终止条件
        if (list.size() == nums.length) {
            ///深度复制
            res.add(new LinkedList<>(list));
            return;
        }
        for (int num : nums) {
            ///如果之前已选择，则选择下一个
            if (seen.contains(num)) continue;
            ///选择当前元素
            list.add(num);
            ///加入已访问列表
            seen.add(num);
            ///下探到下一层
            helper(nums, index + 1, seen, list);
            ///回溯
            list.removeLast();
            seen.remove(num);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
