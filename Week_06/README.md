# 排序算法

## 冒泡排序

```java
    public static int[] bubbleSort(int a[]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }
```

## 插入排序

```java
    public static int[] insertionSort(int a[]) {
        for (int i = 1; i < a.length; i++) {
            int valueToSort = a[i];
            int j;

            for (j = i; j > 0 && a[j - 1] > valueToSort; j--) {
                a[j] = a[j - 1];
            }

            a[j] = valueToSort;
        }

        return a;
    }
```

## 选择排序

```java

    public static int[] selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < a.length; j++)
                if (a[j] < a[index])
                    index = j;

            int smallerNumber = a[index];
            a[index] = a[i];
            a[i] = smallerNumber;
        }
        return a;
    }
```

## 堆排序

```java

    public static int[] heapSort(int[] arr) {
        buildheap(arr);
        int sizeOfHeap = arr.length - 1;
        for (int i = sizeOfHeap; i > 0; i--) {
            exchange(arr, 0, i);
            sizeOfHeap = sizeOfHeap - 1;
            heapify(arr, 0, sizeOfHeap);
        }
        return arr;
    }

    public static void buildheap(int[] arr) {

        /*
         * As last non leaf node will be at (arr.length-1)/2
         * so we will start from this location for heapifying the elements
         * */
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, i, arr.length - 1);
        }
    }

    public static void heapify(int[] arr, int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max;
        if (left <= size && arr[left] > arr[i]) {
            max = left;
        } else {
            max = i;
        }

        if (right <= size && arr[right] > arr[max]) {
            max = right;
        }
        // If max is not current node, exchange it with max of left and right child
        if (max != i) {
            exchange(arr, i, max);
            heapify(arr, max, size);
        }
    }

    public static void exchange(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
```

## 快排

```java

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] array, int left, int right) {
        int i = left;
        int j = right;
        // find pivot number, we will take it as mid
        int pivot = array[left + (right - left) / 2];

        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchange(array, i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (left < j)
            quickSort(array, left, j);
        if (i < right)
            quickSort(array, i, right);
    }
```

## 归并排序

```java
    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    // Recursive algorithm for merge sort
    public static void mergeSort(int[] a, int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            // Sort left half
            mergeSort(a, start, mid);
            // Sort right half
            mergeSort(a, mid + 1, end);
            // Merge left and right half
            merge(a, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        // Initializing temp array and index
        int[] tempArray = new int[arr.length];
        int tempArrayIndex = start;

        int startIndex = start;
        int midIndex = mid + 1;

        // It will iterate until smaller list reaches to the end
        while (startIndex <= mid && midIndex <= end) {
            if (arr[startIndex] < arr[midIndex]) {
                tempArray[tempArrayIndex++] = arr[startIndex++];
            } else {
                tempArray[tempArrayIndex++] = arr[midIndex++];
            }
        }

        // Copy remaining elements
        while (startIndex <= mid) {
            tempArray[tempArrayIndex++] = arr[startIndex++];
        }
        while (midIndex <= end) {
            tempArray[tempArrayIndex++] = arr[midIndex++];
        }

        // Copy tempArray to actual array after sorting
        for (int i = start; i <= end; i++) {
            arr[i] = tempArray[i];
        }
    }
```