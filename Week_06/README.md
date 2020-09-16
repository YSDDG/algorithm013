# 学习笔记

## 动态规划

- 分治+最优子结构

1. 动态规划和递归或者分治没有本质的区别（关键在看是否有最优子结构）
2. 共性：找到重复问题
3. 差异性：最优子结构，中途可以淘汰次优解

### 关键点

1. 最优子结构
2. 储存中间状态
3. 递推公式（状态转移或DP方程）

### 五步动态规划

1. define subproblem 将复杂问题转换成一个简单的子问题

2. guess（part of solution） 

3. relate subproblem solutions

4. recurse & memorize 

   or build DP table bottom-up

5. solve original problem

![image-20200915161115966](C:\Users\HanZhijie\AppData\Roaming\Typora\typora-user-images\image-20200915161115966.png)

## 递归代码模板

```java
// Java
public void recur(int level, int param) { 
  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level: level + 1, newParam); 
  // restore current status 
 
}
```

## 分治代码模板

```java
private static int divide_conquer(Problem problem, ) {
  
  if (problem == NULL) {
    int res = process_last_result();
    return res;     
  }
  subProblems = split_problem(problem)
  
  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])
  
  result = process_result(res0, res1);
  
  return result;
}

```

## 最长公共子序列

### 经验

- 经验1：从最后的字符开始看

- 经验2：最后字符相同，则最长公共子序列为去除最后相同字符的子序列的最长子序列+1

- 经验3：两个字符串最后的变换过程，做成一个二维数组，行和列分别为两个字符串

![image-20200915154735026]( C:\Users\HanZhijie\AppData\Roaming\Typora\typora-user-images\image-20200915154735026.png)





# 动态规划思维小结

1. 打破自己的思维惯性，形成机器思维/算法思维（找重复性）
2. 理解复杂逻辑的关键
3. 也是职业进阶的要点要领

# 动态规划典型题目

1. ## [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/description/)

   - 注意形态 
     - 变形1：一次爬1级2级3级（easy）
     - 变形2：一次爬1级2级3级，但相邻两步不能相同(medium)

2. [三角形最小路径和](https://leetcode-cn.com/problems/triangle/description/)

3. [最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

4. [乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/description/)

5. [零钱兑换](https://leetcode-cn.com/problems/coin-change/description/)

6. [打家劫舍](https://leetcode-cn.com/problems/house-robber/)