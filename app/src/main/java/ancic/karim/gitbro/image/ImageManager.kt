package ancic.karim.gitbro.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

class ImageManager private constructor(context: Context) {
    private val requestManager = Glide.with(context)
    private val requestOptions = RequestOptions()
    private lateinit var requestBuilder: RequestBuilder<Drawable>

    companion object {
        @JvmStatic
        fun with(context: Context) = ImageManager(context)
    }

    fun load(url: String?): ImageManager {
        requestBuilder = requestManager.load(url)
        return this
    }

    fun setCircle(circle: Boolean): ImageManager {
        if (circle) {
            requestOptions.circleCrop()
        }
        return this
    }

    fun into(imageView: ImageView) {
        requestBuilder.apply(requestOptions).into(imageView)
    }
}
