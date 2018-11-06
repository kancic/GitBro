package ancic.karim.gitbro.databinding.item;

import org.jetbrains.annotations.NotNull;

import androidx.databinding.ViewDataBinding;

public interface OnItemClickListener<BINDING extends ViewDataBinding, ITEM> {
    void onItemClick(@NotNull BINDING binding, @NotNull ITEM item, int position);
}
