package ancic.karim.gitbro.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

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