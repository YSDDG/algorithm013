//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 380 👎 0


import javafx.util.Pair;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || beginWord.length() != endWord.length() || !wordList.contains(endWord)) {
            return 0;
        }
        Map<String, List<String>> dict = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                if (dict.containsKey(key)) {
                    dict.get(key).add(word);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(word);
                    dict.put(key, list);
                }
            }
        }
        return bfs(beginWord, endWord, new HashSet<>(), dict);
    }

    private int bfs(String beginWord, String endWord, Set<String> path, Map<String, List<String>> dict) {
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(beginWord, 1));
        while (!queue.isEmpty()) {
            Pair<String, Integer> pair = queue.poll();
            String key = pair.getKey();
            Integer count = pair.getValue();
            for (int i = 0; i < key.length(); i++) {
                String word = key.substring(0, i) + "*" + key.substring(i + 1);
                List<String> list = dict.getOrDefault(word, new ArrayList<>());
                for (String s : list) {
                    if (s.equals(endWord)) {
                        return count + 1;
                    } else {
                        if (!path.contains(s)) {
                            queue.add(new Pair<>(s, count + 1));
                            path.add(s);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
