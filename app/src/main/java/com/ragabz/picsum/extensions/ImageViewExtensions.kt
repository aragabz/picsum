package com.ragabz.picsum.extensions

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.ragabz.picsum.R
import timber.log.Timber
import java.util.*

fun ImageView.loadImageFromUrl(imageUrl: String?) {
    // create request options to set placeholder or error

    val circularProgressDrawable = CircularProgressDrawable(this.context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
    val options = RequestOptions()
        .placeholder(circularProgressDrawable)
        .error(R.color.white)

    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(imageUrl ?: "")
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView.bindLoadImagePalette(url: String, paletteView: View) {
    Timber.i("imageUrl: $url")
    Glide.with(this.context)
        .load(url)
        .listener(
            GlidePalette.with(url)
                .use(BitmapPalette.Profile.MUTED_LIGHT)
                .intoCallBack { palette ->
                    val rgb = palette?.dominantSwatch?.rgb
                    if (rgb != null) {
                        paletteView.setBackgroundColor(rgb)
                    }
                }.crossfade(true)
        ).into(this)
}

fun ImageView.bindRandomColor() {
    this.setBackgroundColor(getRandomColor())
}

fun getRandomColor(): Int {
    val rnd = Random()
    return Color.argb(
        255,
        rnd.nextInt(256),
        rnd.nextInt(256),
        rnd.nextInt(256)
    )
}