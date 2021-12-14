package com.ragabz.picsum.features.pictures_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.ragabz.picsum.base.ViewBindingHolder
import com.ragabz.picsum.databinding.ItemAdsBinding
import com.ragabz.picsum.databinding.ItemPictureBinding
import com.ragabz.picsum.extensions.bindRandomColor
import com.ragabz.picsum.extensions.loadImageFromUrl

class PictureAdapter : ListAdapter<PictureItemViewModel, PictureViewHolder>(
    PictureDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return when (viewType) {
            ItemType.NORMAL.value -> {
                PictureViewHolder(
                    ItemPictureBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                PictureViewHolder(
                    ItemAdsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.binding.apply {
            val item = getItem(position)
            when (this) {
                is ItemAdsBinding -> {
                    imageView.loadImageFromUrl("https://th.bing.com/th/id/R.24f4ee65f671e44d4bc15686dfb068f7?rik=ZqQKqdS%2bBZHVVQ&riu=http%3a%2f%2fwww.prometheusltd.co.uk%2fwp-content%2fuploads%2f2016%2f08%2fvodafone-1-1024x211.jpg&ehk=kfCoxgsREev4P495RO4HLqy0uSObYH5TltMD2pyUwo0%3d&risl=&pid=ImgRaw&r=0")
                }
                is ItemPictureBinding -> {
                    imageView.loadImageFromUrl(item.url)
                    textViewAuthor.text = item.author
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType
    }

    /**
     * picture diff util callback that used in list adapter
     */
    internal class PictureDiffCallback : DiffUtil.ItemCallback<PictureItemViewModel>() {
        override fun areItemsTheSame(
            oldItem: PictureItemViewModel,
            newItem: PictureItemViewModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PictureItemViewModel,
            newItem: PictureItemViewModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    fun getImageUrlForItem(position: Int): String = getItem(position).url
}

class PictureViewHolder(
    binding: ViewBinding
) : ViewBindingHolder(binding)


enum class ItemType(val value: Int) {
    NORMAL(1), ADS(2)
}