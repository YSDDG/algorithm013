//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 示例: 
//
// 你可以将以下二叉树：
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//序列化为 "[1,2,3,null,null,4,5]" 
//
// 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这
//个问题。 
//
// 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。 
// Related Topics 树 设计 
// 👍 330 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

     // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        ///获取深度
        int degree = getMaxDegree(root);
        if (degree > 31) throw new UnsupportedOperationException("超出限制");
        int length = (int) (Math.pow(2, degree) - 1);
        char[] chars = new char[length];
        serialize(root, 0, 1, degree, chars);
        return new String(chars);
    }

    private void serialize(TreeNode root, int index, int level, int degree, char[] chars) {
        if (level > degree) return;
        if (root == null) {
            chars[index] = 'n';
            serialize(null, 2 * index + 1, level + 1, degree, chars);
            serialize(null, 2 * index + 2, level + 1, degree, chars);
            return;
        }
        chars[index] = (char) (root.val + '0');
        serialize(root.left, 2 * index + 1, level + 1, degree, chars);
        serialize(root.right, 2 * index + 2, level + 1, degree, chars);
    }

    private int getMaxDegree(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getMaxDegree(root.left), getMaxDegree(root.right));
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        char[] chars = data.toCharArray();
        return deserialize(0, chars);
    }

    private TreeNode deserialize(int index, char[] chars) {
        if (index >= chars.length) return null;
        if (chars[index] == 'n') return null;
        TreeNode root = new TreeNode(chars[index] - '0');
        root.left = deserialize(2 * index + 1, chars);
        root.right = deserialize(2 * index + 2, chars);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
