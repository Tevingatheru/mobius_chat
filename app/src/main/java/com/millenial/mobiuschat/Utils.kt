package com.millenial.mobiuschat

import android.content.Context
import android.graphics.Bitmap
import android.text.format.DateUtils.isToday
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import java.text.SimpleDateFormat
import java.util.Locale

class Utils {
    companion object {
        fun formatDateTime(timeInMillis: Long): String? {
            return if (isToday(timeInMillis)) {
                formatTime(timeInMillis)
            } else {
                formatDate(timeInMillis)
            }
        }

        private fun formatTime(timeInMillis: Long): String? {
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            return dateFormat.format(timeInMillis)
        }

        private fun formatDate(timeInMillis: Long): String? {
            val dateFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
            return dateFormat.format(timeInMillis)
        }

        /**
         * Crops image into a circle that fits within the ImageView.
         */
        fun displayRoundImageFromUrl(context: Context, url: String?, imageView: ImageView) {
            val myOptions: RequestOptions = RequestOptions()
                .centerCrop()
                .dontAnimate()
            Glide.with(context)
                .asBitmap()
                .apply(myOptions)
                .load(url)
                .into(object : BitmapImageViewTarget(imageView) {
                    protected override fun setResource(resource: Bitmap?) {
                        val circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.resources, resource)
                        circularBitmapDrawable.isCircular = true
                        imageView.setImageDrawable(circularBitmapDrawable)
                    }
                })
        }
    }
}
