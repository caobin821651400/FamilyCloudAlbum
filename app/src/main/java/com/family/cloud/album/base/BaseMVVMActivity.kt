package com.family.cloud.album.base

import android.os.Bundle
import android.view.LayoutInflater
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
abstract class BaseMVVMActivity<VM : BaseViewModule, T : ViewBinding> : BaseActivity() {

    protected lateinit var binding: T
    protected lateinit var mViewModule: VM
    private val type = javaClass.genericSuperclass as ParameterizedType


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val aClass = type.actualTypeArguments[1] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, layoutInflater) as T
        setContentView(binding.root)

        beforeInitUI()
        initUI()
        initEvent()
    }


    override fun beforeInitUI() {
        mViewModule = createViewModel()

        mViewModule.showDialogLiveData.observe(this, Observer { showDlgWithMsg(it) })
        mViewModule.dismissDialogLiveData.observe(this, Observer { disMissDLG() })
    }

    /**
     * 创建viewModel
     */
    protected fun createViewModel(): VM {
        val aClass = type.actualTypeArguments[0] as Class<VM>
        return ViewModelProvider(this).get(aClass)
    }

    protected abstract fun initUI()

    protected abstract fun initEvent()
}