package ancic.karim.gitbro.databinding

import ancic.karim.gitbro.databinding.item.OnItemClickListener
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
open class DataBindingRecyclerViewAdapter<BINDING : ViewDataBinding, ITEM : Any>(protected val variableId: Int, itemList: List<ITEM>, private vararg val listItemResourceViewId: Int) : RecyclerView.Adapter<DataBindingRecyclerViewAdapter.BindingHolder<BINDING>>(), BindingListener<BINDING, ITEM> {
    protected val itemList = ArrayList(itemList)
    protected var selectedVariableId: Int = 0
    protected var selectedItemPosition = -1

    var bindingListener: BindingListener<BINDING, ITEM>? = null
    var onItemClickListener: OnItemClickListener<BINDING, ITEM>? = null

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

    protected open fun onPreBindItem(item: ITEM): ITEM {
        return item
    }

    override fun onBindItem(holder: BindingHolder<BINDING>, item: ITEM, position: Int) {
        bindingListener?.onBindItem(holder, item, position)
    }

    protected fun onItemClickListener(holder: BindingHolder<BINDING>, item: ITEM): View.OnClickListener {
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

    fun update(newItemList: List<ITEM>) {
        itemList.clear()
        itemList.addAll(newItemList)
        notifyDataSetChanged()
    }

    fun remove(item: ITEM): Int {
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

    class BindingHolder<BINDING : ViewDataBinding>(val binding: BINDING) : RecyclerView.ViewHolder(binding.root)
}

interface BindingListener<BINDING : ViewDataBinding, ITEM : Any> {
    fun onBindItem(holder: DataBindingRecyclerViewAdapter.BindingHolder<BINDING>, item: ITEM, position: Int)
}