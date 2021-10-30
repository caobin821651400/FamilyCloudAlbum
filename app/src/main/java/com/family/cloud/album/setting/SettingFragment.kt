package com.family.cloud.album.setting

import cn.sccl.xlibrary.utils.XLogUtils
import com.family.cloud.album.base.BaseMVVMFragment
import com.family.cloud.album.databinding.FragmentSettingBinding

class SettingFragment : BaseMVVMFragment<SettingModule, FragmentSettingBinding>() {


    override fun initUI() {

    }

    override fun initEvent() {
        binding.albumButton.setOnClickListener {
            XLogUtils.d(mViewModule.aa)
        }
    }


}