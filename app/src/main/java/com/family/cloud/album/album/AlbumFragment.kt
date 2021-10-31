package com.family.cloud.album.album

import android.view.View.OnFocusChangeListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.sccl.xlibrary.adapter.XRecyclerViewAdapter
import cn.sccl.xlibrary.adapter.XViewHolder
import cn.sccl.xlibrary.kotlin.setVisible
import com.family.cloud.album.R
import com.family.cloud.album.base.BaseMVVMFragment
import com.family.cloud.album.bean.AlbumListBean
import com.family.cloud.album.databinding.FragmentAlbumBinding
import com.family.cloud.album.view.IconTextButton
import com.family.cloud.album.view.ScaleImage
import com.family.cloud.album.view.ScaleTextView

class AlbumFragment : BaseMVVMFragment<AlbumViewModule, FragmentAlbumBinding>() {


    private lateinit var mAdapter: AlbumAdapter

    override fun initUI() {

        val list = ArrayList<AlbumListBean>()
        list.add(AlbumListBean(AlbumListBean.TYPE_HEADER))
        list.add(AlbumListBean(AlbumListBean.TYPE_TITLE))

        for (i in 0..9) {
            list.add(AlbumListBean(AlbumListBean.TYPE_LIST, i == 9))
        }

        val manager = GridLayoutManager(context, 5)
        binding.albumRv.layoutManager = manager
        mAdapter = AlbumAdapter(binding.albumRv)
        binding.albumRv.adapter = mAdapter
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (mAdapter.getItem(position).itemType) {
                    AlbumListBean.TYPE_LIST -> 1
                    else -> 5
                }
            }
        }
        mAdapter.dataLists = list
    }

    override fun initEvent() {
    }


    inner class AlbumAdapter(mRv: RecyclerView) : XRecyclerViewAdapter<AlbumListBean>(mRv) {

        override fun bindData(holder: XViewHolder, data: AlbumListBean, position: Int) {
            when (data.itemType) {
                AlbumListBean.TYPE_HEADER -> {
                    val btnCommemorate = holder.getView<IconTextButton>(R.id.btn_commemorate)
                    val btnPlace = holder.getView<IconTextButton>(R.id.btn_Place)
                    val btnTimeFilter = holder.getView<IconTextButton>(R.id.btn_time_filter)

                    btnCommemorate.onFocusChangeListener = headerListener
                    btnPlace.onFocusChangeListener = headerListener
                    btnTimeFilter.onFocusChangeListener = headerListener
                }
                AlbumListBean.TYPE_TITLE -> {

                }
                AlbumListBean.TYPE_LIST -> {
                    val img = holder.getView<ScaleImage>(R.id.iv_img)
                    val tvMore = holder.getView<ScaleTextView>(R.id.tv_more)

                    if (data.isMoreItem) {
                        tvMore.setVisible(true)
                        tvMore.text = "100+"
                    } else tvMore.setVisible(false)
                    img.setOnFocusChangeListener { _, hasFocus -> tvMore.scaleMe(hasFocus) }
                }
            }

        }

        private val headerListener =
            OnFocusChangeListener { v, hasFocus ->
                mRv.scrollToPosition(0)
            }

        private val itemListListener =
            OnFocusChangeListener { v, hasFocus ->

            }

        override fun getItemLayoutResId(data: AlbumListBean, position: Int): Int {
            when (data.itemType) {
                AlbumListBean.TYPE_HEADER -> return R.layout.item_album_header
                AlbumListBean.TYPE_TITLE -> return R.layout.item_album_time_title
                AlbumListBean.TYPE_LIST -> return R.layout.item_album_img_list
            }
            return -1
        }
    }
}