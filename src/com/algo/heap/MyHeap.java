package com.algo.heap;

/**
 * 堆划，大顶堆
 *
 * @author soda
 * @date 2022/10/28
 */
public class MyHeap {

    int size = 0;
    int[] nums;

    public MyHeap(int n) {
        nums = new int[n];
    }

    private static void initHeap(int[] nums) {
        //从上往下堆化
        int n = nums.length - 1;
        n = n % 2 == 0 ? n - 1 : n;
        for (int i = n / 2; i >= 0; i--) {
            heaping(nums, i, nums.length);
        }
    }

    private static void heaping(int[] nums, int i, int length) {

        while (true){
            int maxPos = i;
            if (2 * i + 1 < length && nums[maxPos] < nums[2 * i + 1]) {
               maxPos = 2 * i + 1;
            }
            if (2 * i + 2 < length && nums[maxPos] < nums[2 * i + 2]) {
               maxPos = 2 * i + 2;
            }
            if (maxPos == i){
                break;
            }
            swap(nums, i , maxPos);
            i = maxPos;
        }
    }

    /**
     * 增加一个元素
     *
     * @param val
     */
    public void add(int val) {
        if (this.size == nums.length) return;
        size++;
        nums[size - 1] = val;
        //堆化
        int index = size - 1;
        for (int i = index; i > 0; ) {
            int parent = (i % 2 == 0 ? i - 1 : i) / 2;
            if (nums[i] > nums[parent]) {
                swap(nums, i, parent);
                i = parent;
            } else {
                break;
            }

        }
    }

    /**
     * 删除栈顶元素
     */
    public void delete() {
        if (size == 0) return;
        nums[0] = nums[size - 1];
        nums[size - 1] = 0;
        size--;
        for (int i = 0; i < size; ) {
            int next = 0;
            if (nums[i] < nums[2 * i + 1]) {
                swap(nums, i, 2 * i + 1);
                next = 2 * i + 1;
            }
            if (nums[i] < nums[2 * i + 2]) {
                swap(nums, i, 2 * i + 2);
                next = next != 0 ? next : 2 * i + 2;
            }
            if (next == 0) {
                break;
            }
            i = next;
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    /**
     * 自然排序
     *
     * @param nums
     */
    public static void sort(int[] nums) {
        initHeap(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            swap(nums, 0, n - 1 - i);
            heaping(nums, 0,n - 1 - i);
        }
    }

    public static void main(String[] args) {
        // MyHeap myHeap = new MyHeap(30);
        // myHeap.add(2);
        // myHeap.add(2);
        // myHeap.add(4);
        // myHeap.add(5);
        // myHeap.add(3);
        // myHeap.add(5);
        // System.out.println(myHeap.nums[0]);
        // myHeap.delete();
        // System.out.println(myHeap.nums[0]);
        // myHeap.delete();
        // System.out.println(myHeap.nums[0]);
        // myHeap.delete();
        // System.out.println(myHeap.nums[0]);
        // myHeap.delete();
        // System.out.println(myHeap.nums[0]);
        // myHeap.delete();
        // System.out.println(myHeap.nums[0]);
        int[] ints = {4, 3, 1, 5, 3, 2, 6, 9, 10, 23, 12};
        MyHeap.sort(ints);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
