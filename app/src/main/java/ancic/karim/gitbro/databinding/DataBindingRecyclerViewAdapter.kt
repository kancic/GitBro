package ancic.karim.gitbro.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter used for recyclerView with data binding
 * Created by Karim on 6.10.2015..
 */
open class DataBindingRecyclerViewAdapter<BINDING : ViewDataBinding, ITEM_TYPE : Any>(protected val variableId: Int, itemList: List<ITEM_TYPE>, private vararg val listItemResourceViewId: Int) : RecyclerView.Adapter<DataBindingRecyclerViewAdapter.BindingHolder<BINDING>>(), BindingListener<BINDING, ITEM_TYPE> {
    protected val itemList = ArrayList(itemList)
    protected var selectedVariableId: Int = 0
    protected var selectedItemPosition = -1

    private var onItemClickListener: OnItemClickListeners<BINDING, ITEM_TYPE>? = null

    var bindingListener: BindingListener<BINDING, ITEM_TYPE>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<BINDING> {
        val binding = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            listItemResourceViewId[viewType],
            parent,
            false
        )
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder<BINDING>, position: Int) {
        val item = onPreBindItem(itemList[position])

        holder.binding.setVariable(variableId, item)

        if (selectedVariableId != 0) {
            holder.binding.setVariable(selectedVariableId, selectedItemPosition == position)
        }

        holder.binding.executePendingBindings()
        holder.binding.root.setOnClickListener(onItemClickListener(holder, item))
        onBindItem(holder, item, position)
    }

    protected open fun onPreBindItem(item: ITEM_TYPE): ITEM_TYPE {
        return item
    }

    override fun onBindItem(holder: BindingHolder<BINDING>, item: ITEM_TYPE, position: Int) {
        bindingListener?.onBindItem(holder, item, position)
    }

    protected fun onItemClickListener(holder: BindingHolder<BINDING>, item: ITEM_TYPE): View.OnClickListener {
        return View.OnClickListener {
            val position = holder.adapterPosition
            if (selectedVariableId != 0) {
                val oldSelectedItemPosition = selectedItemPosition
                selectedItemPosition = position

                notifyItemChanged(selectedItemPosition)
                notifyItemChanged(oldSelectedItemPosition)
            }

            onItemClickListener?.apply {
                onItemClick(holder.binding, item, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun update(newItemList: List<ITEM_TYPE>) {
        itemList.clear()
        itemList.addAll(newItemList)
        notifyDataSetChanged()
    }

    fun remove(item: ITEM_TYPE): Int {
        val position = itemList.indexOf(item)
        if (position >= 0) {
            itemList.removeAt(position)
            notifyItemRemoved(position)
            return itemList.size
        }
        return 0
    }

    fun setSelectedEnabled(selectedVariableId: Int, selectedItemPosition: Int) {
        this.selectedVariableId = selectedVariableId
        this.selectedItemPosition = selectedItemPosition
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListeners<BINDING, ITEM_TYPE>) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListeners<BINDING : ViewDataBinding, ITEM_TYPE> {
        fun onItemClick(binding: BINDING, item: ITEM_TYPE, position: Int)
    }

    class BindingHolder<BINDING : ViewDataBinding>(val binding: BINDING) : RecyclerView.ViewHolder(binding.root)
}

interface BindingListener<BINDING : ViewDataBinding, ITEM_TYPE : Any> {
    fun onBindItem(holder: DataBindingRecyclerViewAdapter.BindingHolder<BINDING>, item: ITEM_TYPE, position: Int)
}