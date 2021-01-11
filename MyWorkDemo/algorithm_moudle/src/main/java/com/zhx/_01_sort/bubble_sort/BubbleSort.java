package com.zhx._01_sort.bubble_sort;

import com.zhx._01_sort.ISort;
import com.zhx._01_sort.SortTestHelper;

/**
 * 冒泡排序
 * 思想：
 *  相邻两个元素依次比较并交换位置，这样每一轮都把最大的元素推到最后的位置（竖着看就是最上边的位置，故称为冒泡）
 */
public class BubbleSort implements ISort {

    @Override
    public int[] sort(int[] array) {
        System.out.println("BubbleSort start --- ");
        if (array.length == 0) {
            return array;
        }
        //i代表第几轮，一轮确定一个元素的最后位置
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] > array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println("BubbleSort end --- ");
        return array;
    }
}
