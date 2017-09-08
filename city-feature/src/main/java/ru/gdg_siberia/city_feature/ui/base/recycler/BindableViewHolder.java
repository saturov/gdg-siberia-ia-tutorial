package ru.gdg_siberia.city_feature.ui.base.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

abstract public class BindableViewHolder<T> extends RecyclerView.ViewHolder implements Bindable<T> {

    public BindableViewHolder(View itemView) {
        super(itemView);
    }
}
