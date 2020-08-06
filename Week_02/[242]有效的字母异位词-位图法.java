//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 225 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Solution {
        ///长度不同，返回false
        ///分别对字符串的char数组进行排序，得到新的字符串，比较其排序结果是否相同




        ///位图法  原理与哈希表相同
        ///建立一个与字符数量相同的数组，起始为'a'，结束为'z'
        ///遍历字符串s，统计每个字符出现的次数，数组对应下标的元素值递增1
        ///遍历字符串t，数组对应下标的元素值递减1，若出现负值，停止遍历，返回false;
        ///返回true
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            int[] table = new int[26];
            for (int i = 0; i < s.length(); i++) {
                table[s.charAt(i) - 'a']++;
            }
            for (int i = 0; i < t.length(); i++) {
                table[t.charAt(i) - 'a']--;
                if (table[t.charAt(i) - 'a'] < 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
