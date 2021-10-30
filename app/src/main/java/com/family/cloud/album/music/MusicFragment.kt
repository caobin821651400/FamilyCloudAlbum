package com.family.cloud.album.music

import cn.sccl.xlibrary.utils.XLogUtils
import com.family.cloud.album.base.BaseMVVMFragment
import com.family.cloud.album.databinding.FragmentAlbumBinding
import com.family.cloud.album.databinding.FragmentMusicBinding

class MusicFragment: BaseMVVMFragment<MusicViewModule, FragmentMusicBinding>() {



    override fun initUI() {

    }

    override fun initEvent() {
        binding.albumButton.setOnClickListener {
            XLogUtils.d(mViewModule.aa)
        }
    }


}