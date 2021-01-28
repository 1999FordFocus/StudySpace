package com.zhx.greedy.cookies;

import java.util.Arrays;

class Solution {
    public int findChildren(int[] g , int[] s){
        Arrays.sort(g);
        Arrays.sort(s);

        int gi = 0 , si = 0;
        int res = 0;
        while(gi < g.length && si < s.length){
            if (s[si] >= g[gi]){
                res++;
                gi++;
            }
            si++;
        }
        return res;
    }

    public static void main(String[] args) {
        int g1[] = {5,10,2,9,15,9};
        int s1[] = {6,1,20,3,8};
        System.out.println((new Solution()).findChildren(g1, s1));

        int g2[] = {1, 2};
        int s2[] = {1, 2, 3};
        System.out.println((new Solution()).findChildren(g2, s2));
    }
}
