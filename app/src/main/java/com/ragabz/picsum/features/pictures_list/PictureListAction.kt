package com.ragabz.picsum.features.pictures_list

import com.ragabz.picsum.base.Action
import com.ragabz.picsum.models.PictureList

sealed interface PictureListAction : Action {
    object GetPictureList : PictureListAction
    data class ShowError(val error: String) : PictureListAction
    data class ShowPictureList(val list: PictureList, val page: Int) : PictureListAction
}