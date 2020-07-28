//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 
//
// 说明: 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 
//
// 示例: 
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6] 
// Related Topics 数组 双指针 
// 👍 573 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        if (n == 0) {
            return;
        }
        int current = 0;
        int idx1 = 0;
        int idx2 = 0;
        int[] nums3 = new int[m + n];
        while (idx1 < m && idx2 < n) {
            if (nums1[idx1] < nums2[idx2]) {
                nums3[current] = nums1[idx1];
                idx1++;
                current++;
            } else {
                nums3[current] = nums2[idx2];
                idx2++;
                current++;
            }
        }
        if (current < m + n) {
            if (idx1 == m) {
                while (idx2 < n) {
                    nums3[current] = nums2[idx2];
                    idx2++;
                    current++;
                }
            } else {
                while (idx1 < m) {
                    nums3[current] = nums1[idx1];
                    idx1++;
                    current++;
                }
            }
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = nums3[i];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
