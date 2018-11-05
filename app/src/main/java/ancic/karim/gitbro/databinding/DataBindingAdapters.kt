package ancic.karim.gitbro.databinding

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

@BindingAdapter(
    value = ["itemVariableId", "itemSelectedVariableId", "itemSelectedPosition", "itemList", "itemResourceId", "itemBindingListener", "itemOnClick"],
    requireAll = false
)
fun <BINDING : ViewDataBinding> setRecyclerViewAdapter(
    recyclerView: RecyclerView,
    itemVariableId: Int,
    itemSelectedVariableId: Int = 0,
    itemSelectedPosition: Int = -1,
    itemList: List<Nothing>?,
    itemResourceId: Int,
    itemBindingListener: BindingListener<BINDING, Nothing>?,
    itemOnClick: DataBindingRecyclerViewAdapter.OnItemClickListeners<BINDING, Nothing>?
) {
    itemList?.let { itemList ->
        var adapter = recyclerView.adapter
        if (adapter == null) {
            adapter = DataBindingRecyclerViewAdapter<BINDING, Nothing>(itemVariableId, itemList, itemResourceId)
            adapter.setSelectedEnabled(itemSelectedVariableId, itemSelectedPosition)
            adapter.bindingListener = itemBindingListener
            if (itemOnClick != null) {
                adapter.setOnItemClickListener(itemOnClick)
            }
            recyclerView.adapter = adapter
        } else if (adapter is DataBindingRecyclerViewAdapter<*, *>) {
            adapter.update(itemList)
        } else {
            adapter.notifyDataSetChanged()
        }
    }
}