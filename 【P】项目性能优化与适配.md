## 【P】项目性能优化与适配



### 一、性能优化

### 电量优化

Battery Historian工具

使用 Android 8.0 及以上版本的设备时，使用Energy Profiler 可以了解应用在哪里耗用了不必要的电 量。



#### Crash监控

##### Java crash

```
public class CrashHandler implements Thread.UncaughtExceptionHandler {
        private static final String FILE_NAME_SUFFIX = ".trace";
        private static Thread.UncaughtExceptionHandler mDefaultCrashHandler;
        private static Context mContext;

        private CrashHandler() {
        }

        public static void init(@NonNull Context context) { //默认为:RuntimeInit#KillApplicationHandler
            mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
            mContext = context.getApplicationContext();
        }

        /**
         * 当程序中有未被捕获的异常，系统将会调用这个方法 *
         *
         * @param t 出现未捕获异常的线程
         * @param e 得到异常信息
         */
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            try {
                //自行处理:保存本地 or 上传服务器
                
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally { //交给系统默认程序处理
                if (mDefaultCrashHandler != null) {
                    mDefaultCrashHandler.uncaughtException(t, e);
                }
            }
        }
 }
```



##### native crash

Linux中信号机制提供的常见崩溃信号列表：

| **信号** | **描述**                       |
| -------- | ------------------------------ |
| SIGSEGV  | 内存引用无效。                 |
| SIGBUS   | 访问内存对象的未定义部分。     |
| SIGFPE   | 算术运算错误，除以零。         |
| SIGILL   | 非法指令，如执行垃圾或特权指令 |
| SIGSYS   | 糟糕的系统调用                 |
| SIGXCPU  | 超过CPU时间限制。              |
| SIGXFSZ  | 文件大小限制。                 |

一旦出现崩溃信号，Android系统默认缺省操作是直接退出程序。但是系统允许我们给某一个进程的某一个特定信号注册一个相应的处理函数（signal），即对该信号的默认处理动作进行修改。因此NDK Crash的监控可以采用这种信号机制，捕获崩溃信号执行我们自己的信号处理函数从而捕获NDK Crash。



###### 使用BreakPad

Google breakpad是一个跨平台的崩溃转储和分析框架和工具集合，其开源地址是:https://github.co m/google/breakpad。breakpad在Linux中的实现就是借助了Linux信号捕获机制实现的。因为其实现 为C++，因此在Android中使用，必须借助NDK工具。



Android Studio 的安装目录下的 bin\lldb\bin 里面就存在一 个对应平台的 minidump_stackwalk程序 ：

```
nimidump_stackwalk xxxx.dump > crash.txt
```

> Crash.txt

```txt
Operating system: Android
0.0.0 Linux 4.4.124+ #1 SMP PREEMPT Wed Jan 30 07:13:09 UTC
2019 i686
CPU: x86 // abi类型
     GenuineIntel family 6 model 31 stepping 1
     3 CPUs
GPU: UNKNOWN
Crash reason: SIGSEGV //内存引用无效 信号 Crash address: 0x0
Process uptime: not available
Thread 0 (crashed) //crashed:出现crash的线程
0 libbugly.so + 0x1feab //crash的so与寄存器信息
eip = 0xd5929eab
esi = 0xd71a3f04
edx = 0x00000000
Found by: given as instruction pointer in context
ebx = 0x0000000c
ecx = 0xefb19400
esp = 0xffa85f30   ebp = 0xffa85f38
edi = 0xffa86128   eax = 0xffa85f5c
efl = 0x00210286
1 libart.so + 0x5f6a18
eip = 0xef92ea18 esp = 0xffa85f40 ebp = 0xffa85f60 Found by: previous frame's frame pointer
Thread 1 ......
```





使用 Android NDK 里面提供的 addr2line 工具将寄存器地址转换为对应符号.

> Android\Sdk\ndk\21.3.6528147\toolchains\x86-4.9\prebuilt\windows-x86_64\bin\i686-linux- android-addr2line.exe

执行命令：

```
i686-linux-android-addr2line.exe -f -C -e libbugly.so 0x1feab
```







#### APK瘦身

1. 工具使用

   Android Size Analyzer

   Lint 

   参考工具提的建议 ：

   ​	1）webPng 代替png

2. 开启资源缩减

   minifyEnabled true

   shrinkResource true

3. 删除不会使用的备用资源

   resConfig 指定需要的语言资源

   

4. 动态库打包配置

   so 一般使用armeabi-v7a

   也可以通过 productFlavor 指定不同渠道包使用不同版本的so



5. 矢量图

   更小、分辨率无关

   tint着色器

   VectorDrawable渲染耗时，建议只对图标使用

6. 精简依赖库，对于分模块的库按需引入

7. 开启资源混淆（R8替代Proguard）

   android.enableR8=true 

   android.enableR8.libraries=true

   

> 微信AndResGuard原理

https://github.com/shwenzhang/AndResGuard



> 很多资源包，如何瘦身。



>  Facebook Rxdex 字节码优化，

删除 **debugInfo** 和减少跨 **Dex** 调用的情况



#### 参考资源

- Android开发高手课 -- 模块一 高质量开发篇

  https://time.geekbang.org/column/article/70602

  

- jessonChao APM技术博文

​	https://juejin.cn/post/6844903972587716621







#### 数据结构的优化

##### ArrayList 与 LinkedList

ArrayList 内部使用数组，随机访问效率高

添加、删除涉及到扩容和元素移动，效率低



LinkedList 采用双向链表

查找慢

插入、删除结点快



##### HashMap原理，有什么性能问题

JDK 1.7 时代。数组+链表

JDK 1.8 后 数组+链表+红黑树

key int装箱



###### SpareAray 

SparseArray只能存储key为int类型的数据，同时，SparseArray在存储和读取数据时候，使用的是二分查找法,也就是在put添加数据的时候，会使用二分查找法和之前的key比较当前我们添加的元素的key的大小，然后按照从小到大的顺序排列好，所以，SparseArray存储的元素都是按元素的key值从小到大排列好的。 



满足下面两个条件我们可以使用SparseArray代替HashMap：

- 数据量不大，最好在千级以内
- key必须为int类型，这中情况下的HashMap可以用SparseArray代替：



######  ArrayMap

ArrayMap是一个<**key,value**>映射的数据结构，它设计上更多的是考虑内存的优化，内部是使用两个数组进行数据存储，一个数组记录key的hash值，另外一个数组记录Value值，它和SparseArray一样，也会对key使用二分法进行从小到大排序，在添加、删除、查找数据的时候都是先使用二分查找法得到相应的index



**总结**

1、如果key的类型已经确定为int类型，那么使用SparseArray，因为它避免了自动装箱的过程，如果key为long类型，它还提供了一个LongSparseArray来确保key为long类型时的使用

2、如果key类型为其它的类型，则使用ArrayMap







### 二、屏幕适配

Android设备屏幕分辨率与屏幕像素密度对照表：

![image-20210103140439077](./images/image-20210103140439077.png)



使用dp（密度无关像素，density-independent pixel）可以保证在不同屏幕像素密度的设备上显示相同的效果。

**dp**与**px**的转换
$$
px = dp * (dpi / 160)
$$


在Android中，规定以160dpi(即屏幕分辨率为320x480)为基准:1dp=1px。主流Android设备，大多是2倍屏、3倍屏



设计稿一般按360dp屏幕宽度来设计，这就导致我们即便按照dp来指定布局大小，仍然会有适配的问题。





#### 今日头条适配方案

修改系统density：

```java
    private static float sNoncompatDensity;// 系统的Density
    private static float sNoncompatScaleDensity;// 系统的ScaledDensity

    private static void setCustomDensity(Activity activity, final Application application){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if(sNoncompatDensity == 0){
            // 系统的Density
            sNoncompatDensity = appDisplayMetrics.density;
            // 系统的ScaledDensity
            sNoncompatScaleDensity = appDisplayMetrics.scaledDensity;
            // 监听在系统设置中切换字体
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration newConfig) {
                    if(newConfig != null && newConfig.fontScale > 0){
                        sNoncompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        // 此处以360dp的设计图作为例子
        final float targetDensity = appDisplayMetrics.widthPixels / 360;
        final float targetScaledDensity = targetDensity * (sNoncompatScaleDensity/sNoncompatDensity);
        final int targetDensityDpi = (int)(160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

```

在Activity的onCreate方法中

```java
@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setCustomDensity(this,this.getApplication());
        setContentView(R.layout.activity_main);
    }
```



#### 约束布局

Bias 方向偏移 取值0～1 默认0.5

weight 权重

chain属性 



#### 多套Dimen方案





#### AutoSize









### 三、机型&SDK版本适配

- Fragment onAttatch() 方法版本兼容

  低于api 23 通过 onAttatch(Activity activity)

   api 23 以后 通过 onAttatch(Context context)



- ​     DialogFragment 6.0以下自带标题栏

  window.requestFeature(Window.FEATURE_NO_TITLE);

- Webview 绘制 低版本设备，绘制，需要手动开启硬件加速

  高版本设备自动开启









