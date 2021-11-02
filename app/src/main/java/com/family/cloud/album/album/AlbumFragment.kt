package com.family.cloud.album.album

import android.view.View.OnFocusChangeListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.sccl.xlibrary.adapter.XRecyclerViewAdapter
import cn.sccl.xlibrary.adapter.XViewHolder
import cn.sccl.xlibrary.kotlin.dp2px
import cn.sccl.xlibrary.kotlin.setVisible
import com.family.cloud.album.MainActivity
import com.family.cloud.album.MainViewModule
import com.family.cloud.album.R
import com.family.cloud.album.base.BaseMVVMFragment
import com.family.cloud.album.bean.AlbumListBean
import com.family.cloud.album.databinding.FragmentAlbumBinding
import com.family.cloud.album.view.*

class AlbumFragment : BaseMVVMFragment<AlbumViewModule, FragmentAlbumBinding>() {

    private val acViewModule by activityViewModels<MainViewModule>()
    private lateinit var mAdapter: AlbumAdapter
    private var isShowFilterView: Boolean = false

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
        binding.timeFilterView.visibilityChange {
            if (!it) {
                mAdapter.btnTimeFilter?.requestFocus()
                isShowFilterView = false
            }
        }

        binding.albumRv.onBorderListener = object : TvRecyclerView.SimpleBorderListener() {
            override fun onLeftBorder(): Boolean {
                acViewModule.mFocusLeftEvent.value = MainActivity.FRG_TYPE_ALBUM
                return true
            }
        }
        binding.rootView.onBorderListener = object : AlbumFragmentRootView.OnBorderListener {
            override fun onLeftBorder(): Boolean {
                if (mAdapter.btnCommemorate?.isFocused == true || mAdapter.btnTimeFilter?.isFocused == true) {
                    acViewModule.mFocusLeftEvent.value = MainActivity.FRG_TYPE_ALBUM
                    return true
                }
                return false
            }

            override fun onTopBorder(): Boolean {
                return false
            }

            override fun onRightBorder(): Boolean {
                if (mAdapter.btnPlace?.isFocused == true || mAdapter.btnTimeFilter?.isFocused == true) {
                    return true
                }
                return false
            }

            override fun onBottomBorder(): Boolean {
                return false
            }
        }
    }

    /**
     * 时间筛选
     */
    fun showTimeFilterView() {
        if (isShowFilterView) {
            hideTimeFilterView()
            return
        }
        mAdapter.btnTimeFilter?.post {
            val top = dp2px(178f)
            val params = binding.timeFilterView.layoutParams as ConstraintLayout.LayoutParams
            params.setMargins(0, top, 0, 0)
            binding.timeFilterView.setVisible(true)
            isShowFilterView = true
            binding.timeFilterView.requestFocus()
        }
    }

    /**
     * 时间筛选
     */
    private fun hideTimeFilterView() {
        mAdapter.btnTimeFilter?.post {
            binding.timeFilterView.setVisible(false)
            isShowFilterView = false
            mAdapter.btnTimeFilter!!.requestFocus()
        }
    }

    inner class AlbumAdapter(mRv: RecyclerView) : XRecyclerViewAdapter<AlbumListBean>(mRv) {

        var btnTimeFilter: IconTextButton? = null
        var btnPlace: IconTextButton? = null
        var btnCommemorate: IconTextButton? = null

        override fun bindData(holder: XViewHolder, data: AlbumListBean, position: Int) {
            when (data.itemType) {
                AlbumListBean.TYPE_HEADER -> {
                    btnCommemorate = holder.getView(R.id.btn_commemorate)
                    btnPlace = holder.getView(R.id.btn_Place)
                    btnTimeFilter = holder.getView(R.id.btn_time_filter)

                    btnCommemorate?.onFocusChangeListener = headerListener
                    btnPlace?.onFocusChangeListener = headerListener
                    btnTimeFilter?.onFocusChangeListener = headerListener
                    //日期筛选
                    btnTimeFilter?.setOnClickListener { showTimeFilterView() }
                    //纪念相册
                    mAdapter.btnCommemorate?.setOnClickListener {
                        launchActivity(AlbumListActivity::class.java, null)
                    }
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