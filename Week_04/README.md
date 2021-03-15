学习笔记

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
    subProblems = split_problem(problem);  
    res0 = divide_conquer(subProblems[0]);  
    res1 = divide_conquer(subProblems[1]);   
    result = process_result(res0, res1);    
    return result;
}

```
## 其他

1. 人肉递归效率低
2. 找到最简方法，将其拆解成可重复解决的问题
3. 数学归纳法思维
4. 动态规划的核心是寻找递推公式
