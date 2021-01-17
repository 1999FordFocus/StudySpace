package com.zhx.myworkdemo.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.zhx.myworkdemo.MyApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 可以显示行数的log,减少打log的复杂度
 */

public class Logger {
    private static boolean sShowLog = true;

    /**
     * 是否保存为日志文件，debug apk专用，且自行预先解决外部存储权限
     */
    private static boolean sStoreLog = true;

    public static void p(String message, Object... params) {
        StringBuilder sb = new StringBuilder(message);
        sb.append(message);
        sb.append(" ");
        for (Object p : params) {
            sb.append("=");
            sb.append(String.valueOf(p));
        }
        Logger.d(sb.toString());
    }

    public static void v(String msg) {
        doLog(1, null, msg);
    }

    public static void v(String tag, String msg) {
        doLog(1, tag, msg);
    }

    public static void e(String msg) {
        doLog(2, null, msg);
    }

//    public static void e(String tag, String msg) {
//        doLog(2, tag, msg);
//    }

    public static void e(String format, Object... param) {
        doLog(2, null, String.format(Locale.getDefault(), format, param));
    }

    public static void d(String msg) {
        doLog(3, null, msg);
    }

    public static void v(String format, Object... param) {
        doLog(1, null, String.format(Locale.getDefault(), format, param));
    }

    private static void doLog(int id, String tag, String message) {
        if (!sShowLog) {
            return;
        }
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int index = 4;
        String className = stackTraceElements[index].getFileName();
        String methodName = stackTraceElements[index].getMethodName();
        int lineNumber = stackTraceElements[index].getLineNumber();

        String t = tag == null ? "zhx" : tag;
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        StringBuilder sb = new StringBuilder();
        if (sShowLog) {
            sb.append(time()).append("[(").append(className).append(":").append(lineNumber).append(")#").append(methodName).append("]");
        }
        sb.append(message);
        switch (id) {
            case 1:
                Log.v(t, sb.toString());
                break;
            case 2:
                Log.e(t, sb.toString());
                break;
            case 3:
                Log.d(t, sb.toString());
                break;
            default:
        }
        //写入外部存储
        write(sb.append("\n").toString());
    }

    /**
     * 标识每条日志产生的时间
     *
     * @return
     */
    private static String time() {
        return "["
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(
                System.currentTimeMillis())) + "]";
    }

    /**
     * 保存到日志文件
     *
     * @param content
     */
    public static synchronized void write(String content) {
        if (!sStoreLog) {
            return;
        }

        if (MyApplication.getContext() == null) {
            return;
        }

        boolean hasPermission = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasPermission = ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }

        if (!hasPermission) {
            return;
        }

        try {
            FileWriter writer = new FileWriter(getFile(), true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取日志文件路径
     *
     * @return
     */
    public static String getFile() {
        File cacheDir = new File(Environment.getExternalStorageDirectory() + File.separator + "com.zhx.demo" + File.separator + "log");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }

        File filePath = new File(cacheDir, "log_" + date() + ".txt");

        return filePath.toString();
    }

    /**
     * 以年月日作为日志文件名称
     *
     * @return
     */
    private static String date() {
        return "["
                + new SimpleDateFormat("yyyy-MM-dd").format(new Date(
                System.currentTimeMillis())) + "]";
    }

    public static class CrashHandler implements Thread.UncaughtExceptionHandler {
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
                //自行处理:保存本地
                File file = dealException(e); //上传服务器
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally { //交给系统默认程序处理
                if (mDefaultCrashHandler != null) {
                    mDefaultCrashHandler.uncaughtException(t, e);
                }
            }
        }

        /**
         * 导出异常信息到SD卡 *
         *
         * @param e
         */
        private File dealException(Thread t, Throwable e) throw Exception

        {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
                    Date());
            File f = new
                    File(context.getExternalCacheDir().getAbsoluteFile(), "crash_info");
            if (!f.exists()) {
                f.mkdirs();
            }
            File crashFile = new File(f, time + FILE_NAME_SUFFIX);
            File file = new File(PATH + File.separator + time + FILE_NAME_SUFFIX);
//往文件中写入数据
            PrintWriter pw = new PrintWriter(new BufferedWriter(new
                    FileWriter(file)));
            pw.println(time);
            pw.println("Thread: " + t.getName());
            pw.println(getPhoneInfo());
            e.printStackTrace(pw); //写入crash堆栈 pw.close();
            return file;
        }

        private String getPhoneInfo() throws PackageManager.NameNotFoundException {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            StringBuilder sb = new StringBuilder(); //App版本
            sb.append("App Version: ");
            sb.append(pi.versionName);
            sb.append("_");
            sb.append(pi.versionCode + "\n");
//Android版本号
            sb.append("OS Version: ");
            sb.append(Build.VERSION.RELEASE);
            sb.append("_");
            sb.append(Build.VERSION.SDK_INT + "\n");
//手机制造商
            sb.append("Vendor: ");
            sb.append(Build.MANUFACTURER + "\n");
//手机型号
            sb.append("Model: ");
            sb.append(Build.MODEL + "\n");
            //CPU架构
            sb.append("CPU: ");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                sb.append(Arrays.toString(Build.SUPPORTED_ABIS));
            } else {
                sb.append(Build.CPU_ABI);
            }
            return sb.toString();
        }
    }
