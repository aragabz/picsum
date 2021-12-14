package com.ragabz.picsum.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ragabz.picsum.R
import com.ragabz.picsum.base.ViewBindingActivity
import com.ragabz.picsum.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ViewBindingActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    override fun onInitBinding() {

    }
}