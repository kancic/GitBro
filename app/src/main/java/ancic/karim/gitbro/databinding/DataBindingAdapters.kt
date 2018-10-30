package ancic.karim.gitbro.databinding

import ancic.karim.gitbro.image.ImageManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["imageUrl", "imageCircle"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?, circle: Boolean = false) {
    ImageManager.with(imageView.context).load(url).setCircle(circle).into(imageView)
}

@BindingAdapter(
    value = ["itemVariableId", "itemSelectedVariableId", "itemSelectedPosition", "itemList", "itemResourceId", "itemOnClick"],
    requireAll = false
)
fun setRecyclerViewAdapter(
    recyclerView: RecyclerView,
    itemVariableId: Int,
    itemSelectedVariableId: Int = 0,
    itemSelectedPosition: Int = -1,
    itemList: List<Nothing>?,
    itemResourceId: Int,
    itemOnClick: DataBindingRecyclerViewAdapter.OnItemClickListeners<ViewDataBinding, Nothing>?
) {
    itemList?.let { itemList ->
        var adapter = recyclerView.adapter
        if (adapter == null) {
            adapter = DataBindingRecyclerViewAdapter<ViewDataBinding, Nothing>(itemVariableId, itemList, itemResourceId)
            adapter.setSelectedEnabled(itemSelectedVariableId, itemSelectedPosition)
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