package com.family.cloud.album

import android.app.Application
import cn.sccl.xlibrary.XLibrary

class App : Application() {

    override fun onCreate() {
        super.onCreate()


        XLibrary.init(this)
    }

}