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

