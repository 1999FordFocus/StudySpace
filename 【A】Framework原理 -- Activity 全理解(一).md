## 【A】四大组件 -- Activity 全理解（一）



### 一 、startActivity 新知识

#### taskAffinity 实现任务栈的调配；

#### 通过 Binder 传递数据的限制；

#### 多进程应用可能会造成的问题；

#### 后台启动 Activity 的限制。





### 项目踩坑记录

1. **Activity onBackPressed回调未被调用 ？**

   检查是不是Fragment已经监听了onKey() 等事件，消耗掉了back键事件。

   > 源码追踪



### 资源参考

 **Android工程师进阶34讲** -- Android 是如何通过 Activity 进行交互的？

https://kaiwu.lagou.com/course/courseInfo.htm?sid=&courseId=67&lagoufrom=noapp#/detail/pc?id=1867