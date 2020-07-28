//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1173 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        boolean flag0 = l2.val < l1.val;
        ListNode node = flag0 ? l2 : l1;
        ListNode currentNode = node;
        ListNode node1 = flag0 ? l1 : l1.next;
        ListNode node2 = flag0 ? l2.next : l2;

        while (node1 != null && node2 != null) {
            boolean flag = node2.val < node1.val;
            currentNode.next = flag ? node2 : node1;
            node1 = flag ? node1 : node1.next;
            node2 = flag ? node2.next : node2;
            currentNode = currentNode.next;
        }
        if (node1 == null) {
            currentNode.next = node2;
        }
        if (node2 == null) {
            currentNode.next = node1;
        }
        return node;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
