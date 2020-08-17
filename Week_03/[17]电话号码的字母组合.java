//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法 
// 👍 822 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private static final Map<Character, String[]> dict;

    static {
        dict = new HashMap<>();
        dict.put('2', new String[]{"a", "b", "c"});
        dict.put('3', new String[]{"d", "e", "f"});
        dict.put('4', new String[]{"g", "h", "i"});
        dict.put('5', new String[]{"j", "k", "l"});
        dict.put('6', new String[]{"m", "n", "o"});
        dict.put('7', new String[]{"p", "q", "r", "s"});
        dict.put('8', new String[]{"t", "u", "v"});
        dict.put('9', new String[]{"w", "x", "y", "z"});
    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        helper(0, digits, "", list);
        return list;
    }

    private void helper(int level, String digits, String tmpStr, List<String> res) {
        if (digits.length() == 0) return;
        //终止条件
        if (level == digits.length()) {
            res.add(tmpStr);
            return;
        }
        //当前层逻辑
        char letter = digits.charAt(level);
        if (dict.containsKey(letter)) {
            String[] chars = dict.get(letter);
            for (String aChar : chars) {
                //下探下一层
                helper(level + 1, digits, tmpStr + aChar, res);
            }
        }
        //清除当前状态
    }
}
//leetcode submit region end(Prohibit modification and deletion)
