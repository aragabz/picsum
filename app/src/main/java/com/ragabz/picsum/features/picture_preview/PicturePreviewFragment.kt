package com.ragabz.picsum.features.picture_preview

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.chrisbanes.photoview.OnViewDragListener
import com.ragabz.picsum.R
import com.ragabz.picsum.base.ViewBindingFragment
import com.ragabz.picsum.databinding.FragmentPicturePreviewBinding
import com.ragabz.picsum.extensions.bindLoadImagePalette
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PicturePreviewFragment : ViewBindingFragment<FragmentPicturePreviewBinding>(
    FragmentPicturePreviewBinding::inflate
) {
    val args: PicturePreviewFragmentArgs by navArgs()
    var imageUrl: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageUrl = args.imageUrl
    }

    override fun onInitBinding() {
        binding.imageView.bindLoadImagePalette(imageUrl, binding.constraintLayoutMain)
    }


}