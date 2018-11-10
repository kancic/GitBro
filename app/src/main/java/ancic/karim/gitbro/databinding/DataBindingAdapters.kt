package ancic.karim.gitbro.databinding

import ancic.karim.gitbro.databinding.item.OnItemClickListener
import ancic.karim.gitbro.image.ImageManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ru.noties.markwon.Markwon
import ru.noties.markwon.SpannableConfiguration
import ru.noties.markwon.il.AsyncDrawableLoader
import java.text.DecimalFormat

@BindingAdapter(value = ["amount"], requireAll = false)
fun setAmount(textView: TextView, amount: Double) {
    val formattedAmount = DecimalFormat.getInstance().format(amount)
    if (formattedAmount != textView.text.toString()) {
        val selectionStart = Math.min(formattedAmount.length, textView.selectionStart)
        val selectionEnd = Math.min(formattedAmount.length, textView.selectionEnd)
        textView.text = formattedAmount
        if (textView is EditText) {
            textView.setSelection(selectionStart, selectionEnd)
        }
    }
}

@BindingAdapter(value = ["markdown"])
fun TextView.setMarkdown(markdown: String?) {
    if (!markdown.isNullOrEmpty()) {
        val configuration = SpannableConfiguration.builder(context).asyncDrawableLoader(AsyncDrawableLoader.create()).build()
        Markwon.setMarkdown(this, configuration, markdown)
    }
}

@BindingAdapter(value = ["imageUrl", "imageCircle"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?, circle: Boolean = false) {
    ImageManager.with(imageView.context).load(url).setCircle(circle).into(imageView)
}

@Suppress("UNCHECKED_CAST", "NAME_SHADOWING")
@BindingAdapter(
    value = ["itemVariableId", "itemSelectedVariableId", "itemSelectedPosition", "itemList", "itemResourceId", "itemBindingListener", "itemOnClick"],
    requireAll = false
)
fun <BINDING : ViewDataBinding, ITEM : Any> setRecyclerViewAdapter(
    recyclerView: RecyclerView,
    itemVariableId: Int,
    itemSelectedVariableId: Int = 0,
    itemSelectedPosition: Int = -1,
    itemList: List<ITEM>?,
    itemResourceId: Int,
    itemBindingListener: BindingListener<BINDING, ITEM>?,
    itemOnClick: OnItemClickListener<BINDING, ITEM>?
) {
    val adapter = recyclerView.adapter
    when (adapter) {
        null -> itemList?.apply {
            recyclerView.adapter = DataBindingRecyclerViewAdapter<BINDING, ITEM>(itemVariableId, itemList, itemResourceId).apply {
                setSelectedEnabled(itemSelectedVariableId, itemSelectedPosition)
                bindingListener = itemBindingListener
                onItemClickListener = itemOnClick
            }
        }
        is DataBindingRecyclerViewAdapter<*, *> -> {
            adapter as DataBindingRecyclerViewAdapter<BINDING, ITEM>
            adapter.update(itemList)
        }
        else -> adapter.notifyDataSetChanged()
    }
}

@BindingAdapter(value = ["show"])
fun showProgressBar(contentLoadingProgressBar: ContentLoadingProgressBar, show: Boolean?) {
    if (show == true) {
        contentLoadingProgressBar.show()
    } else {
        contentLoadingProgressBar.hide()
    }
}