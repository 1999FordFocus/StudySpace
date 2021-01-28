## 【P】项目问题整理

> 项目中遇到过什么问题，如何解决 ？
>



### 173

1. ##### 【**线上问题排查】BadTokenException**

   ```
   android.view.WindowManager$BadTokenException: Unable to add window -- token android.os.BinderProxy@d4cacc6 is not valid; is your activity running?
       at android.view.ViewRootImpl.setView(ViewRootImpl.java:837)
       at android.view.WindowManagerGlobal.addView(WindowManagerGlobal.java:356)
       at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:94)
       at android.app.Dialog.show(Dialog.java:329)
       at com.wanmei.show.fans.util.Utils.a(Utils.java:184)
       at com.wanmei.show.fans.util.Utils.a(Utils.java:158)
       at com.wanmei.show.fans.ui.base.BaseRoomActivity.a(BaseRoomActivity.java:60)
       at com.wanmei.show.fans.ui.playland.VideoLandActivity.a(VideoLandActivity.java:151)
       at com.wanmei.show.fans.ui.playland.VideoLandActivity$4.a(VideoLandActivity.java:272)
       at com.wanmei.show.fans.ui.play.OnlineBeat.d(OnlineBeat.java:109)
       at com.wanmei.show.fans.ui.play.OnlineBeat.b(OnlineBeat.java:21)
       at com.wanmei.show.fans.ui.play.OnlineBeat$3.a(OnlineBeat.java:99)
       at com.wanmei.show.fans.http.SocketUtils.a(SocketUtils.java:494)
       at com.wanmei.show.fans.http.SocketUtils.a(SocketUtils.java:561)
       at com.wanmei.show.fans.http.SocketUtils.a(SocketUtils.java:1073)
       at com.wanmei.show.fans.ui.play.OnlineBeat.c(OnlineBeat.java:81)
       at com.wanmei.show.fans.ui.play.OnlineBeat.a(OnlineBeat.java:21)
       at com.wanmei.show.fans.ui.play.OnlineBeat$2.run(OnlineBeat.java:72)
       at android.os.Handler.handleCallback(Handler.java:873)
       at android.os.Handler.dispatchMessage(Handler.java:99)
       at android.os.Looper.loop(Looper.java:201)
       at android.app.ActivityThread.main(ActivityThread.java:6864)
       at java.lang.reflect.Method.invoke(Native Method)
       at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:547)
       at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:873)
   ```

   源码追踪

   ```
   /**
    * Exception that is thrown when trying to add view whose
    * {@link LayoutParams} {@link LayoutParams#token}
    * is invalid.
    */
   public static class BadTokenException extends RuntimeException {
       public BadTokenException() {
       }
   
       public BadTokenException(String name) {
           super(name);
       }
   }
   ```



2. **【线上问题排查】SVGA动画内存溢出**

   ```
   java.lang.OutOfMemoryError: Failed to allocate a 120012 byte allocation with 43176 free bytes and 42KB until OOM
       at dalvik.system.VMRuntime.newNonMovableArray(Native Method)
       at android.graphics.BitmapFactory.nativeDecodeByteArray(Native Method)
       at android.graphics.BitmapFactory.decodeByteArray(BitmapFactory.java:533)
       at com.opensource.svgaplayer.SVGAVideoEntity.a(SVGAVideoEntity.kt:88)
       at com.opensource.svgaplayer.SVGAVideoEntity.<init>(SVGAVideoEntity.kt:57)
       at com.opensource.svgaplayer.SVGAParser.a(SVGAParser.kt:182)
       at com.opensource.svgaplayer.SVGAParser.a(SVGAParser.kt:25)
       at com.opensource.svgaplayer.SVGAParser$parse$3.invoke(SVGAParser.kt:95)
       at com.opensource.svgaplayer.SVGAParser$parse$3.invoke(SVGAParser.kt:25)
       at com.wanmei.show.fans.ui.play.gift.common.DynamicEffectUtil$2$1.a(DynamicEffectUtil.java:153)
       at okhttp3.RealCall$AsyncCall.d(RealCall.java:141)
       at okhttp3.internal.NamedRunnable.run(NamedRunnable.java:32)
       at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1133)
       at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:607)
       at java.lang.Thread.run(Thread.java:761)
   ```

3. **【线上问题排查】Toast 添加到windowManager 异常**

```
java.lang.IllegalStateException: View android.widget.LinearLayout{9cb9554 V.E...... ......ID 0,0-690,147} has already been added to the window manager.
	at android.view.WindowManagerGlobal.addView(WindowManagerGlobal.java:419)
	at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:118)
	at android.widget.Toast$TN.handleShow(Toast.java:536)
	at android.widget.Toast$TN$1.handleMessage(Toast.java:435)
	at android.os.Handler.dispatchMessage(Handler.java:110)
	at android.os.Looper.loop(Looper.java:219)
	at android.app.ActivityThread.main(ActivityThread.java:8349)
	at java.lang.reflect.Method.invoke(Native Method)
	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:513)
	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1055)
```

> 引申问题 ：Toast 为什么不需要弹窗权限



https://juejin.cn/post/6844903674066518024







#### 验证码SDK

1. WebView绘制与  js中innerWidth、innerHeight获取问题







### OneSDK

**【问题描述】可正常打包但是安装时报签名错误，对此Apk反编译后再打包签名对齐后可正常是什么原因？**
目前对此现象进行分析，发现新引入的一个jar包包含一个 META_INF/MANIFEST.MF文件，该文件只包含一个回车符。

将此文件从该jar中删除，即可恢复正常，如果把该文件中的回车符删除使之成为一个空文件，也可正常安装打包后得到的apk。

推测可能签名对此文件有BUG。





















