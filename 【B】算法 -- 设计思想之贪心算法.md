## 【B】算法 -- 设计思想之贪心算法

### SelfCheck

- 最长上升子序列问题
- 



### 理解贪心算法

着眼当下，选择当前最佳的方案。



#### 实战分析 1-- 分糖果

我们有 m 个糖果和 n 个孩子（m < n)，糖果不够分。糖果大小不同s1,s2,s3...sm，孩子对糖果大小的需求也不同，需求因子g1,g2,g3....gn，且孩子只有达到了对糖果大小的需求才能够满足。如何分配糖果，才能满足最多数量的孩子？

举例：需求因子g = [5,10,2,9,15,9],糖果的大小数组为：s = [6,1,20,3,8],最多满足3个孩子。



1.将糖果和孩子数组分别排好序

2.优先用小糖果满足需求最小的孩子，因为如果孩子的需求能用小糖果满足，就不用浪费大糖果，相反，如果小糖果连需求最小的孩子都没法满足，那也肯定不能满足需求比他大的孩子。

3.直到没有孩子或糖果。

```
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
```



#### 实战分析 2-- 钱币找零

有1，3，7三个面值的金钱，现在要取n元。怎么取个数最少。（n是已知数）

```
public static void cash2(int n) {
	System.out.println(" change : "+n);
	int left = n;
	if(left >= 7) {
		int count_7 = n / 7;
		System.out.println(" count_7 : "+count_7);
		count+=count_7;
		int mod_7 = n % 7;
		left = mod_7;
	}

	if(left >= 3) {
		int count_3 = left / 3;
		System.out.println(" count_3 : "+count_3);
		count+=count_3;
		int mod_3 = left % 3;
		left = mod_3;
	}

	if(left > 0) {
		int count_1 = left / 1;
		count+=count_1 / 1;
		System.out.println(" count_1 : "+count_1);
	}

	System.out.println(" change over , count is "+count);
}
```





### 贪心算法应用场景

#### 数据压缩 -- 霍夫曼编码（Huffman Coding）



#### Prim和Kruskal最小生成树算法



#### Dijkstra单源最短路径算法