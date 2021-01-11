> **路漫漫其修远兮，吾将上下而求索** 



## C++ 开篇 -- 从C到C++

### 面试问题

1. C++面向对象特性
2. 讲一下内联函数 ，内联函数和#define区别。inline 内联什么时候失效？为什么循环、分支不能设为内联？如果设为内联会怎样？
3. 静态链接和动态链接的区别？哪种更快？哪种生成的可执行文件更大？
4. main函数之前和之后都会做什么工作
5. 内存空间分为哪几个部分？全局变量存在哪个部分？
6. 指针和引用的区别
7. 





###  IDE下载安装

1. CLion + MinGW64 ( Android开发者对 jetbean出品的编译器用着顺手)

https://www.cnblogs.com/attentle/p/12652512.html

  

> ​    CLion中文乱码问题解决：
>

```
 打开File - Settings -> File Encodings编码改成UTF-8

 编译器右下角改成GBK
```



> Mac升级Catalina(10.15)后 clion不能运行，提示「xcrun: error: invalid active developer path ...」

```
1.是因为升级后少了系统需要的运行库，重新安装Xcode developer tools即可解决该问题。在终端中运行命令行xcode-select --install 后会自动安装Xcode developer tools，安装成功后即可解决该问题。

2.Tools -> CMmake -> reset cache and reload project
```



### C++ 知识大纲



封装

继承

多态

模版













### 第一个c++程序

 输入半径，求圆的周长面积:

> 思考类与对象的关系：c++编译器是如何区分是c1,c2,c3对象调用了类的方法？



```c++
#include <iostream>
/**
 * 第一个c++程序：求圆的周长和面积
 * @return
 */
using namespace std;

class MyCircle {
public:
    double getMR() {
        return m_r;
    }

    void setMR(double mR) {
        m_r = mR;
    }

    double getMS() {
        m_s = 3.14 * m_r * m_r;
        return m_s;
    }

private:
    double m_r;
    double m_s;
};

int main() {
    cout << "请输入半径：" << std::endl;
    MyCircle c1{};
    double r;
    cin >> r;
    c1.setMR(r);
    cout << "圆的面积是：" << c1.getMS() << std::endl;
    return 0;
}
```





### C/C++文件编译过程

1. 预处理

   把#include包含进来的.h文件 插入到#include所在位置，把源程序中使用到的#define定义的宏用实际的字符串替换。

   如果不用IDE，对应命令为：

   `gcc -E main.c -o main.i`

2. 编译

   编译阶段，编译器检查代码的规范性、语法错误等，检查无误后。编译器把代码翻译成汇编语言

   如果不用IDE，对应命令为：

   `gcc -S main.i -o main.s`

3. 汇编

   汇编阶段把.s文件翻译成二进制指令文件.o，这个阶段接收.c，.i ,.s的文件都没有问题

   如果不用IDE，对应命令为：

   `gcc -C main.s -o main.o`

4. 链接

   链接阶段，链接的是其余的函数库，比如我们自己编写的c/c++文件中用到了三方的函数库

   如果不用IDE，对应命令为：

   `gcc main.o -o main..exe`





### C++对c语言的扩展

- #### namespace作用域

  ```c++
  namespace mine{}
  ```

  控制标识符作用域，c语言只有一个全局作用域

- #### register 函数检测增强

  for循环中 int i ，c++会自行优化为寄存器变量

- #### struct类型类型加强

  c语言中认为struct定义了一组变量的集合，而不是一种类型

  c++中struct关键字和class关键字功能一样的，但是还是有区别,class成员默认private，struct默认public

- #### c++更强调类型（所有的变量和函数都必须有类型）

- #### const 不同表现

  c语言中const的表现：

  ​      const int *a; //const 修饰指针变量指向的内存空间 

  ​      int const *a; //const修饰指针变量本身

  ​	  const int const *a // 都不能修改

  但是c语言中const是个“冒牌货”，修饰的变量不是真的常量（可以通过指针修改）

  ```c
  const int  a = 10;
  int *p = NULL;
  p = &a;
  *p =  20;
  ```

  

  c++ 中const修饰的是一个真正的常量；原因是c++编译器扫描到const int a = 10; 时会把 a =10 以key-value形式放到符号表里。当const常量为全局，并且需要在其他文件中使用的时候、当使用&操作取const常量地址的是时候会分配新的内存空间，而不是在原变量a上直接操作。

  

  const内存分配机制：

  在编译器编译期间为p  = (int *)&a;分配内存

  

  const用途 -- 替代#define

  ​		相同点：

  ​				作用类似: const int a =5 ,#define a 5

  ​		不同点：
  
  #define由预处理器处理,是单纯的文本替换（没有作用域限制，除非使用#undef a卸载掉）const由编译器处理，提供类型和作用域检查。                                                                                                                                                                



-  #### **引用** 

  此处简要介绍引用相关的知识点

  1. 引用概念和用法
  
     变量的别名
  
     ```c++
     int a = 10;
     int &b = a;
     cout<<"b = "<<b;
     b = 100;
 cout<<"a = "<<b;
     ```

     > 引用的性质：普通引用必须初始化（依附于某个变量）
  
  2. **引用作函数参数、引用作返回值、引用作左值**
  
     ```c++
     int& getA2()
     {  
     	int a =10;  
     	return a;
 }
     ```

     如果函数返回栈上的引用（引用作返回值），可能会有问题。不可以作为其他引用的初始值（不能用其他引用变量来接收）

     函数返回值是引用（一块内存空间），用什么类型去接收，结果不同

  
​	   返回引用的函数当左值（特别适用于链式编程）
  
​			
  
  3. 复杂数据类型的引用
  
  ​        别名等同于指针方式修改内存
  
  

而下面函数对于形参pT的修改，不会影响到实参

```c++
void printfT(Teacher pT)
{  
    cout<<"age1 = "<<pT.age<<endl;   
    pT.age = 45;
}
```

​			4. 引用的本质

​				普通引用内部实现是指针常量，有自己的内存空间，也跟原变量操作同一块内存。

​			5.指针的引用

​			6. 常量引用

- #### 新增bool类型

  占用 1个字节，值为0或1

- #### 三目运算符 

  c语言中表达式的返回的是一个值，

  c++中返回的是变量本身（内存空间地址）。

  等同于如下c语言实现上述效果：

  ​	*(a < b ? &a : &b) = 30;

- #### 内联函数

- #### 函数

  ​	默认参数

  ​	函数占位参数

  ​	默认参数和占位参数混搭

  ​	函数重载

- #### new、delete



### 













### 学习资源

- 慕课网-C++远征系列课（共七篇）

- B站 - 黑马程序员教程
  https://b23.tv/WbL7JB

  

































