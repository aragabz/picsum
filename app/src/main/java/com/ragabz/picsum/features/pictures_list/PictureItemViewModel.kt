package com.ragabz.picsum.features.pictures_list

import androidx.lifecycle.ViewModel
import com.ragabz.picsum.models.PictureModel

class PictureItemViewModel(
    val itemType: Int,
    private val pictureModel: PictureModel
) : ViewModel() {

    val id: String
        get() = pictureModel.id

    val url: String
        get() = pictureModel.downloadUrl

    val height: Int
        get() = pictureModel.height

    val width: Int
        get() = pictureModel.width

    val author: String
        get() = pictureModel.author
}