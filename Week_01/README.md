# 学习笔记

## 改写 Deque 的代码

要求：使用 add first 或 add last 这套新的 API 改写 Deque 的代码

```java
class Solution {
    public static void replace() {
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);

        String str = deque.peekFirst();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.pollFirst());
        }

        System.out.println(deque);
    }
}
```

## Queue 源码分析

## Priority Queue 源码分析

## HashMap 源码分析

## 设计循环双端队列