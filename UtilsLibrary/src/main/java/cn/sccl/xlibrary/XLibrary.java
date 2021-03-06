package cn.sccl.xlibrary;


import android.content.Context;


public class XLibrary {
    private static Context context;

    // #log
    public static String logTag = "家庭云->";
    public static boolean isDebug = true;


    public static void init(Context context) {
        XLibrary.context = context;
    }

    public static Context getContext() {
        synchronized (XLibrary.class) {
            if (XLibrary.context == null)
                throw new NullPointerException("XLibrary未初始化");
            return XLibrary.context.getApplicationContext();
        }
    }
}
