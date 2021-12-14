package com.ragabz.picsum.features.pictures_list

import androidx.annotation.BoolRes
import com.ragabz.picsum.base.State
import com.ragabz.picsum.models.PictureList

data class PictureListStateView(
    val isLoading: Boolean = false,
    val pictureList: PictureList = mutableListOf(),
    val error: String = "",
    val page: Int = 1
) : State
