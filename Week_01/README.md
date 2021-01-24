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

**Stack 数据结构推荐使用 ArrayQueue**

### ArrayQueue

see https://www.cnblogs.com/CarpenterLee/p/5468803.html

![](https://images2015.cnblogs.com/blog/939998/201605/939998-20160507172937875-1444735637.png)

- head 是第一个元素的下标
- tail 是最后一个元素下一个位置的下标

#### addFirst

![](https://images2015.cnblogs.com/blog/939998/201605/939998-20160507172943265-2092213314.png)

`head = (head - 1) & (elements.length - 1)`  强势解决 head 越界问题

#### addLast

![](https://images2015.cnblogs.com/blog/939998/201605/939998-20160507172955312-946261107.png)

`(tail = (tail + 1) & (elements.length - 1)` 强势解决 tail 越界问题

#### ensureCapacity

![](https://images2015.cnblogs.com/blog/939998/201605/939998-20160507172951125-776162110.png)

分配一个原容量两倍的空间，将 head -> tail 的数据复制到新数组的开头

### LinkedList

就是 创建节点，添加到链表中，没啥好说的，因为每次都需要 `new Node()` 所以如果有可替代的 Array 实现，推荐使用 Array 的，减少内存多次分配可能带来的性能问题

## Priority Queue 源码分析

see https://www.cnblogs.com/CarpenterLee/p/5488070.html

最小堆实现，可以配置 `Comparator` 使其变为最大堆。

![](https://images2015.cnblogs.com/blog/939998/201605/939998-20160512205540484-823563038.png)

### add/offer

![](https://images2015.cnblogs.com/blog/939998/201605/939998-20160512205600890-346195840.png)

元素添加到最尾端，然后进行 siftUp

### remove

![](https://images2015.cnblogs.com/blog/939998/201605/939998-20160512205651859-11099237.png)

若移除元素是最后一个，直接移除，否则将最后一个元素移动到删除元素的位置，然后 siftDown

### poll

poll 是特殊的 remove，他移除的是下标为 0 位置的元素

![](https://images2015.cnblogs.com/blog/939998/201605/939998-20160512205634609-402016454.png)

最后一个元素移动到下标 0，然后进行 siftDown

## HashMap 源码分析

see https://tech.meituan.com/2016/06/24/java-hashmap.html

![](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2016/e4a19398.png)

- threshold是在Load factor和length(数组长度)对应下允许的最大元素数目，超过这个数目就重新resize(扩容)，扩容后的HashMap容量是之前容量的两倍
    - threshold = load factor * length
- modCount字段主要用来记录HashMap内部结构发生变化的次数，主要用于迭代的快速失败。强调一点，内部结构发生变化指的是结构发生变化，例如put新键值对，但是某个key对应的value值被覆盖不属于结构变化
- 当链表长度太长（默认超过8）时，链表就转换为红黑树，利用红黑树快速增删改查的特点提高HashMap的性能

### table 下标计算

![](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2016/45205ec2.png)

1. key hashCode 计算，（PS：hashCode 是 32bit的int）
2. hashCode 高16位低16位做异或
3. (容量-1) & 异或结果 = 下标位置

### put

![](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2016/d669d29c.png)

- put 完后判断扩容

### 扩容机制

1. 申请容量为原容量两倍的数组
2. 遍历 table, 对于每个下标位置的链或者红黑树上的元素重新计算下标

### 转红黑树为什么是8个

see https://blog.csdn.net/reliveIT/article/details/82960063

```
// 泊松分布 http://en.wikipedia.org/wiki/Poisson_distribution
     * 0:    0.60653066
     * 1:    0.30326533
     * 2:    0.07581633
     * 3:    0.01263606
     * 4:    0.00157952
     * 5:    0.00015795
     * 6:    0.00001316
     * 7:    0.00000094
     * 8:    0.00000006
```

对于HashMap.table[].length的空间来说，放入0.75*length个数据，某一个bin中放入节点数量的概率情况如上图（表示数组某一个下标存放数据数量为0~8时的概率情况）

- HashMap默认的table[].length=16，在长度为16的HashMap中放入12（0.75*length）个数据，某一个bin中存放了8个节点的概率是0.00000006
- 扩容一次，16*2=32，在长度为32的HashMap中放入24个数据，某一个bin中存放了8个节点的概率是0.00000006
- 再扩容一次，32*2=64，在长度为64的HashMap中放入48个数据，某一个bin中存放了8个节点的概率是0.00000006

## 设计循环双端队列

```java
package s0600;

public class N0641DesignCircularDeque {
    Object[] elements;
    int head;
    int tail;
    int size;
    
    public N0641DesignCircularDeque(int k) {
        elements = new Object[k];
    }
    
    public boolean insertFront(int value) {
        if (isFull()) return false;
        if (isEmpty()) return insertLast(value);

        elements[head = (head - 1 + elements.length) % elements.length] = value;
        size++;
        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) return false;

        elements[tail] = value;
        tail = (tail + 1 + elements.length) % elements.length;
        size++;
        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) return false;

        head = (head + 1 + elements.length) % elements.length;
        size--;
        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) return false;

        tail = (tail - 1 + elements.length) % elements.length;
        size--;
        return true;
    }
    
    public int getFront() {
        if (isEmpty()) return -1;

        return (int) elements[head];
    }
    
    public int getRear() {
        if (isEmpty()) return -1;

        return (int) elements[(tail - 1 + elements.length) % elements.length];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == elements.length;
    }
}
```