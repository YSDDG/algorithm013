//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 8814 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        ///遍历数组，若哈希表中有对应当前所遍历元素的目标元素，则返回该元素和当前元素的下标
        ///若不存在目标元素，则将数组元素和下标加入哈希表中    
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (cache.containsKey(target - nums[i])) return new int[]{i, cache.get(target - nums[i])};
            cache.put(nums[i], i);
        }
        return new int[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
