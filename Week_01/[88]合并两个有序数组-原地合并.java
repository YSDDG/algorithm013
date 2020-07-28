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
        int current = m + n - 1;
        int idx1 = m - 1;
        int idx2 = n - 1;
        while (current >= 0 && idx1 >= 0 && idx2 >= 0) {
            boolean flag = nums1[idx1] > nums2[idx2];
            nums1[current] = flag ? nums1[idx1] : nums2[idx2];
            current--;
            if (flag) idx1--;
            else idx2--;
        }
        if (idx1 < 0) {
            while (idx2 >= 0) {
                nums1[current] = nums2[idx2];
                current--;
                idx2--;
            }
        } else {
            while (idx1 >= 0) {
                nums1[current] = nums1[idx1];
                current--;
                idx1--;
            }
        }
    }}
//leetcode submit region end(Prohibit modification and deletion)
