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

    private void helper(int[] nums, int index, Set<Integer> seen, LinkedList<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }
        for (int num : nums) {
            if (seen.contains(num)) continue;
            list.add(num);
            seen.add(num);
            helper(nums, index + 1, seen, list);
            list.removeLast();
            seen.remove(num);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
