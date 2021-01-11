## 【A】高级UI系列一：从布局文件解析过程看 Activiy、Window、View的关系

> 先搞懂ViewGroup和View的绘制流程，再去学如何自定义View/ViewGroup。



### 输出倒逼输入

- 

- canvas怎么来的。

- window跟view的关系。
- badwindowtoken的原因。

- 

  





### 前言

平时开发，只要在Activity 的onCreate方法中通过setContentView()指定xml布局文件，然后就可以dataBinding、ButterKnife等各种骚操作了。那么我们指定的xml布局是怎么样加载、被解析成View的，如何添加到Window上成为Activity 的onResume 后“视图可见”状态的，又是谁发起的View内容绘制的呢？



### 思维导图















### 参考资源

- Android图形显示系统（一）

  https://blog.csdn.net/a740169405/article/details/70548443







































