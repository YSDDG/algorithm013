学习笔记

------



## 递归

### 模板

1. recursion terminator
2. process logic in current level
3. drill down
4. reverse the current level status if needed

```java
//terminator
if(level>max_level){
//process result
return;
}
//process current logic
process(level,param);

//drill down
recur(level:level+1,newParam);

//restore current status
```

------

### 注意事项：

- 不要人肉递归

- 找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）
- 数学归纳法的思维

------

### 分治

#### 代码模板

```java
///Java
private static int divide_conquer(Problem problem) {
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

### 回溯

代码模板

