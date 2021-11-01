package com.family.cloud.album

import cn.sccl.http.core.BaseViewModule
import com.family.cloud.album.utils.SingleLiveEvent
/**
 * ====================================================
 * @User :caobin
 * @Date :2021/11/1 22:35
 * @Desc :
 * ====================================================
 */
class MainViewModule : BaseViewModule() {


    val mFocusLeftEvent = SingleLiveEvent<String>()

    val aaa: String = "2313"


}