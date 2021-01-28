## 【A】高级UI -- 开篇

> 集中注意力是造就**心流**的关键 
>
> 米哈里问读者：什么是自得其乐？他自己的回答是：“就是‘拥有自足目标的自我’"
>
> 瑜伽的目标与本书所描述的心流活动多么接近 -- “控制内心所发生的一切”



### 输出倒逼输入

- View绘制机制，onMeasure onLayout ,onDraw方法的调用机制谈一下

- 硬件加速的底层实现。

- 双缓冲机制。

- 矢量图的原理。

- 【设计】设计检测过度绘制的工具，要具体到view。

- 

- 自定义控件有哪几种实现方式？在实现自定义控件时需要重写哪几个构造方法？ 

- 自定义view的步骤，Android的wrap_content是如何计算的

- 定一个宽高相同的ImageView，你怎么做，重写哪些方法 

- 如果我要显示一张图片，要求一定要4:3显示(onMeasure的setMeasureDimension)

- **使用自动布局，把三个view横向等宽等间距（与父view边距也相等）排列。**

- 如何绘制大于父view的子view

- 

- 对ACTION_CANCEL事件的理解，什么时候触发 

- **事件分发机制/****点击事件流程，**touch事件的传递机制和函数调用栈

- 给出一个activity的布局：activity里包含viewgroup1，viewgroup1里包含viewgroup2，viewgroup2里包含button，问touch事件的传递和处理机制；

- **多点触控如何传递**

- 

- Android中你认为哪种动画最为强大？简述其实现原理

- 动画的实现

- 

- ListView性能优化，以及ListView的各种原理，listview内点击buttom并移动的事件流完整拦截过程

- **RecyclerView优点，原理，**说下RecyclerView回收池原理

- ListView和RecyclerView？在ListView或者RecyerView中给一个itemView，怎么得到这个View中数据在Datas的索引 

- 为什么RecyclerView加载首屏会慢一些，recycleview的性能指标

- CoordinatorLayout、CollapsingToolbarLayout原理以及使用

  





### 知识体系导图

- View绘制原理
- 自定义View/ViewGroup基本流程
  - FlowLayout实战
  - 自定义Banner实战
- View事件分发机制
  
  - PhotoView事件分发
- 滑动冲突处理
  - 京东淘宝首页二级联动
  - NestedScrollView嵌套滑动机制分析
  - 协调者布局 CoordinatorLayout、CollapsingToolbarLayout
- Android 动画实战与原理
  
  - 灵动的锦鲤
- 常用UI组件 -- FrameLayout、LinearLayout
- 常用UI组件 -- RecycleView原理



### 文章导航

高级UI系列一：从布局文件解析过程看 Activiy、Window、View的关系

高级UI系列二：View的绘制过程

高级UI系列三：自定义View/ViewGroup基本流程、LinearLayout

高级UI系列四：自定义ViewGroup实践 -- 流式布局FlowLayout

高级UI系列五：自定义View实践 -- 灵动的锦鲤

高级UI系列六：View事件分发机制

高级UI系列七：View事件分发实战--自定义PhotoView

高级UI系列八：解决滑动冲突、NestedScrollView嵌套滑动机制分析

高级UI系列九：RecycleView使用与原理

高级UI系列十：View动画应用与原理



### 参考资源

- 享学课堂 -- 高级UI

  

- 源码分析Android UI何时刷新Choreographer

​		https://www.jianshu.com/p/d7be5308d06e

- VSYNC信号的理解

  https://www.jianshu.com/p/99617dacc7dc