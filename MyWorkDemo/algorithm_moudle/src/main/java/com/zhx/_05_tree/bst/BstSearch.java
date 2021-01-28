package com.zhx._05_tree.bst;

/**
 * 二分查找法
 */
public class BstSearch {
    public static int find(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return find(arr, 0, arr.length - 1, target);
    }

    private static int find(int[] arr, int l, int r, int target) {
        if (l > r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (target < arr[mid]) {
            return find(arr, l, mid - 1, target);
        } else if (target > arr[mid]) {
            return find(arr, mid + 1, r, target);
        } else {
            return mid;
        }
    }
}
