package com.zhx._01_sort.selection_sort;

import com.zhx._01_sort.ISort;

/**
 * 选择排序
 * 思想：也是两轮循环，外循环index表示排到了哪个位置，内循环从该位置向后寻找更小的元素，并记录其下标，最后交换两元素交换位置
 */
public class SelectionSort implements ISort {
    @Override
    public int[] sort(int[] array) {
        System.out.println("SelectionSort start --- ");
        int n = array.length;

        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        System.out.println("SelectionSort end --- ");
        return array;
    }
}
