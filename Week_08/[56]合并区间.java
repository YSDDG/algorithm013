//给出一个区间的集合，请合并所有重叠的区间。 
//
// 
//
// 示例 1: 
//
// 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: intervals = [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。 
//
// 
//
// 提示： 
//
// 
// intervals[i][0] <= intervals[i][1] 
// 
// Related Topics 排序 数组 
// 👍 614 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    时间复杂度:快排：O(nlogn);
    遍历数组(O(n))
    拷贝数组(O(k))
    最终时间复杂度：O(nlogn)

    空间复杂度: 结果数组必须
    最坏情况下，浪费n-1个数组空间
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][2];
        quickSort(intervals, 0, intervals.length - 1);
        int[][] ret = new int[intervals.length][2];
        ret[0] = intervals[0];
        int count = 0;
        for (int[] interval : intervals) {
            int[] prev = ret[count];
            if (prev == null) {
                ret[count] = interval;
            } else {
                if (prev[1] < interval[0]) {
                    ret[++count] = interval;
                } else {
                    ret[count][1] = Math.max(prev[1], interval[1]);
                }
            }
        }
        int[][] res = Arrays.copyOfRange(ret, 0, count + 1);
        return res;
    }

    public void quickSort(int[][] nums, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(nums, begin, end);
        quickSort(nums, begin, pivot - 1);
        quickSort(nums, pivot + 1, end);
    }

    private int partition(int[][] nums, int begin, int end) {
        int pivot = end;
        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (nums[i][0] < nums[pivot][0]) {
                int[] temp = nums[counter];
                nums[counter] = nums[i];
                nums[i] = temp;
                counter++;
            }
        }
        int[] temp = nums[pivot];
        nums[pivot] = nums[counter];
        nums[counter] = temp;
        return counter;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
