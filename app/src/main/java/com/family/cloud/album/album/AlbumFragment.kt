package com.family.cloud.album.album

import cn.sccl.xlibrary.utils.XLogUtils
import com.family.cloud.album.base.BaseMVVMFragment
import com.family.cloud.album.databinding.FragmentAlbumBinding

class AlbumFragment: BaseMVVMFragment<AlbumViewModule, FragmentAlbumBinding>() {



    override fun initUI() {

    }

    override fun initEvent() {
        binding.albumButton.setOnClickListener {
            XLogUtils.d(mViewModule.aa)
        }
    }


}