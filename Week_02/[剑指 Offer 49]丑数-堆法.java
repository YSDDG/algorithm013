//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 数学 
// 👍 54 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    static Ugly ugly = new Ugly();

    public int nthUglyNumber(int n) {
        //堆
        return ugly.nums[n - 1];
    }

    ///预计算1690个丑数
    ///第一个丑数为1，放入堆中以及已找到的集合中
    ///set集合代表已经找到的丑数
    ///迭代，取出堆顶元素，加入结果集中；并分别与2，3，5相乘得到新的丑数，若之前未找到，加入到堆中
    ///完成预计算
    static class Ugly {
        int[] nums = new int[1690];
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();

        public Ugly() {
            heap.add(1L);
            seen.add(1L);
            long currUgly = 0;
            long newUgly = 0;
            int[] primes = new int[]{2, 3, 5};
            for (int i = 0; i < 1690; i++) {
                currUgly = heap.poll();
                nums[i] = (int) currUgly;
                for (int prime : primes) {
                    newUgly = prime * currUgly;
                    if (!seen.contains(newUgly)) {
                        heap.add(newUgly);
                        seen.add(newUgly);
                    }
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
