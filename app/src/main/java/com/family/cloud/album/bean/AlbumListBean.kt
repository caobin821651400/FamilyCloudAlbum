package com.family.cloud.album.bean

data class AlbumListBean(
    val itemType: Int = TYPE_LIST,
    val isMoreItem: Boolean = false
) {


    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_TITLE = 1
        const val TYPE_LIST = 2

    }
}