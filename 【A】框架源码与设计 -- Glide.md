## 【A】框架源码与设计 -- 图片加载框架 Glide



### SelfCheck

- 来简单介绍下Glide的缓存
- Glide的三级缓存原理
- Glide加载一个1M的图片（100 * 100），是否会压缩后再加载，放到一个300 * 300的view上会怎样，800*800呢，图片会很模糊，怎么处理？
- 简单说一下内存泄漏的场景，如果在一个页面中使用Glide加载了一张图片，图片正在获取中，如果突然关闭页面，这个页面会造成内存泄漏吗？
- 如何设计一个大图加载框架





### Glide 源码分析

#### 基本使用 -- API



#### 设计思想



#### with



#### load



#### Into



### 手写网络框架

##### 设计思路

图片加载包含封装，解析，下载，解码，变换，缓存，显示等操作。

![img](/Users/zhanghongxi/ABP-study/StudySpace/images/2019112206351331.png)



1. 封装参数：从指定来源，到输出结果，中间可能经历很多流程，所以第一件事就是封装参数，这些参数会贯穿整个过程；
2. 解析路径：图片的来源有多种，格式也不尽相同，需要规范化；
3. 读取缓存：为了减少计算，通常都会做缓存；同样的请求，从缓存中取图片（Bitmap）即可；
4. 查找文件/下载文件：如果是本地的文件，直接解码即可；如果是网络图片，需要先下载；
5. 解码：这一步是整个过程中最复杂的步骤之一，有不少细节，下个博客会说；
6. 变换：解码出Bitmap之后，可能还需要做一些变换处理（圆角，滤镜等）；
7. 缓存：得到最终bitmap之后，可以缓存起来，以便下次请求时直接取结果；
8. 显示：显示结果，可能需要做些动画（淡入动画，crossFade等）。





### 参考资源

https://liuyangbajin.blog.csdn.net/article/details/103193470?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.control