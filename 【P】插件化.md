## 【P】插件化

### 插件化基础知识

####  对startActivity过程的hook







### 插件化核心技术

#### 资源的解决方案

##### Android 资源加载与查找过程









#### Activity的插件化解决方案





#### Service的插件化解决方案





#### BroadcastReceiver的插件化解决方案





#### ContentProvider的插件化解决方案





#### so的插件化解决方案



### 插件化解决方案 

#### 简易动态加载框架



#### 插件化换肤方案



#### 基于静态代理的插件化解决方案-- that框架



#### 基于Fragment的插件化框架







### 市面上主流动态化框架研究

![img](/Users/zhanghongxi/ABP-study/StudySpace/images/27f332588dae2d5f4d1a210e3e77dc73.png)



#### 热修复框架面对的SDK版本兼容挑战

![img](/Users/zhanghongxi/ABP-study/StudySpace/images/85a4abdd15b978a0f2ec2dec8d9298fa.png)

Google 在 Android P 新增了AppComponentFactory API，并且在 Android Q 增加了替换 Classloader 的接口 instantiateClassloader。在 Android Q 以后，我们可以实现在运行时替换已经存在 ClassLoader 和四大组件。我们使用 Google 官方 API 就可以实现热修复了。

![img](/Users/zhanghongxi/ABP-study/StudySpace/images/2c57e70df59f3db4ee33e96819c3ced3.png)



### 学习资源

- 《插件化开发指南》-- 包建强

- Android 开发高手课 -- 张绍文

  https://time.geekbang.org/column/article/89555