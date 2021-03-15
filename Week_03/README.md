# 学习笔记

## 二分查找

使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方。同学们可以将自己的思路、代码写在学习总结中。

```python
def find(nums: [int]) -> int:
    if not nums: return -1
    if nums[0] < nums[len(nums)-1]: return -1
    l, h = 0, len(nums)-1
    while l < h:
        mid = l + (h - l) // 2
        if nums[mid] > nums[mid + 1]: return mid + 1
        if nums[mid - 1] > nums[mid]: return mid
        if nums[0] < nums[mid]: l = mid + 1
        else: h = mid - 1
    return -1
```