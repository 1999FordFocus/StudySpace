## 【A】Android虚拟机原理--开篇 

> 开篇里，记录一些ART 和 Dalvik 虚拟机共同的知识。
>
> 

### 输出倒逼输入

- Java程序启动过程（5个过程）；
- Java内存回收机制（包括回收算法）；
- jvm线程私有的有哪些

- **java类加载过程，ClassLoader 的双亲委派机制**
- 内存中堆与栈的区别，什么时候在栈中什么时候在堆中
- java gc是如何回收对象的，可以作为gc根节点的对象有哪些？
- jvm的内存模型是什么样的？如何理解java的虚函数表？
- **引用，（强、弱、软、虚，以及之间的异同）**
- Dalvik和Art虚拟机区别？
- dex对class的优化、odex对dex的优化、
- .oat与.art文件



### 正文

#### ART 与 Dalvik区别与改进





#### Class文件格式

Class文件格式总览

常量池与常量项

常量池（Constant Pool）是由常量项组成的数组，常量项数据结构伪代码：

```
cp_info{
	u1 tag; //1个字节， 表明常量项的类型
	u1 info[]; //常量项具体内容
}
```







#### Dex文件格式



#### ELF文件格式



#### 





### 参考资源

- 官方文档 -- ART和Dalvik

https://source.android.com/devices/tech/dalvik/