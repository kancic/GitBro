package ancic.karim.gitbro.databinding

import ancic.karim.gitbro.databinding.item.OnItemClickListener
import ancic.karim.gitbro.image.ImageManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
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
    itemList?.let { itemList ->
        var adapter = recyclerView.adapter
        if (adapter == null) {
            adapter = DataBindingRecyclerViewAdapter<BINDING, ITEM>(itemVariableId, itemList, itemResourceId).apply {
                setSelectedEnabled(itemSelectedVariableId, itemSelectedPosition)
                bindingListener = itemBindingListener
                onItemClickListener = itemOnClick
            }
            recyclerView.adapter = adapter
        } else if (adapter is DataBindingRecyclerViewAdapter<*, *>) {
            adapter as DataBindingRecyclerViewAdapter<BINDING, ITEM>
            adapter.update(itemList)
        } else {
            adapter.notifyDataSetChanged()
        }
    }
}