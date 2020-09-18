# 学习笔记

## 字典树

树的兄弟结点英文：siblings

1. 数据结构
   - 树形结构
2. 核心思想
3. 基本性质
   - 结点本身不存完成单词
   - 从根节点到某一节点，路径上经过的字符连接起来，为该结点对应的字符串
   - 每个结点的所有子结点路径代表的字符串都不相同
4. 优点
   - 最大限度减少无谓的字符串比较，查询效率比哈希表高

### 代码模板

```java
//Java
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}
```



## 并查集

### 用途

- 组团、配对问题

  ```java
  // Java
  class UnionFind { 
  	private int count = 0; 
  	private int[] parent; 
  	public UnionFind(int n) { 
  		count = n; 
  		parent = new int[n]; 
  		for (int i = 0; i < n; i++) { 
  			parent[i] = i;
  		}
  	} 
  	public int find(int p) { 
  		while (p != parent[p]) { 
  			parent[p] = parent[parent[p]]; 
  			p = parent[p]; 
  		}
  		return p; 
  	}
  	public void union(int p, int q) { 
  		int rootP = find(p); 
  		int rootQ = find(q); 
  		if (rootP == rootQ) return; 
  		parent[rootP] = rootQ; 
  		count--;
  	}
  }
  ```

  ### 基本操作

  1. makeSe(s) 创建并查集，其中包含s个单元素的集合
  2. unionSet(x,y) 钯元素x、y所造的集合合并 要求x、y所在的集合不想交，如果相交则不合并
  3. find(x) 找到元素x所在的集合的代表该操作也可以用来判断两个元素是否在同一个集合，只要将它们各自的代表比较一下就好了

  ### 实战题目

  1. [朋友圈](https://leetcode-cn.com/problems/friend-circles)
  2. [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)
  3. [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)

## 高级搜索

### 朴素搜索的优化方式

- 去重复、剪枝

### 搜索方向

- **DFS**
- **BFS**
- **双向搜索**、**启发式搜索**

### DFS代码模板

```java
//Java
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null){
            return allResults;
        }
        travel(root,0,allResults);
        return allResults;
    }


    private void travel(TreeNode root,int level,List<List<Integer>> results){
        if(results.size()==level){
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if(root.left!=null){
            travel(root.left,level+1,results);
        }
        if(root.right!=null){
            travel(root.right,level+1,results);
        }
    }
```

### BFS代码模板

```java
//Java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) {
        return allResults;
    }
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            results.add(node.val);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        allResults.add(results);
    }
    return allResults;
}

```

### 剪枝

### 双向BFS