package com.family.cloud.album.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import cn.sccl.http.core.BaseViewModule
import java.lang.reflect.ParameterizedType


/**
 * ====================================================
 * @User :caobin
 * @Date :2020/8/19 22:28
 * @Desc :BaseViewModule Activity
 * ====================================================
 */
abstract class BaseMVVMFragment<VM : BaseViewModule, T : ViewBinding> : BaseFragment() {

    protected lateinit var mViewModule: VM
    protected lateinit var binding: T
    private val type = javaClass.genericSuperclass as ParameterizedType

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val aClass = type.actualTypeArguments[1] as Class<*>
        val method = aClass.getDeclaredMethod(
            "inflate", LayoutInflater::class.java,
            ViewGroup::class.java, Boolean::class.java
        )
        binding = method.invoke(null, layoutInflater, container, false) as T
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val aClass = type.actualTypeArguments[0] as Class<VM>
        mViewModule = ViewModelProvider(this).get(aClass)

        mViewModule.showDialogLiveData.observe(this, Observer { showDlgWithMsg(it) })
        mViewModule.dismissDialogLiveData.observe(this, Observer { disMissDLG() })
        initUI()
        initEvent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


    protected abstract fun initUI()

    protected abstract fun initEvent()
}