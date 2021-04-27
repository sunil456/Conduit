package io.sunil.conduit.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

fun ImageView.loadImage(uri: String, circleCrop: Boolean = false){
    if (circleCrop){
        Glide.with(this).load(uri).into(this)
    }
    else{
        Glide.with(this).load(uri).circleCrop().into(this)
    }

}