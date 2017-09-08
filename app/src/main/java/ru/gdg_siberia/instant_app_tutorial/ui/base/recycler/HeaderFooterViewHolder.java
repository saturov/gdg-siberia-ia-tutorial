package ru.gdg_siberia.instant_app_tutorial.ui.base.recycler;

import android.view.View;
import android.view.ViewGroup;

public class HeaderFooterViewHolder extends BindableViewHolder<View> {

    public HeaderFooterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(View bindItem) {
        ViewGroup container = (ViewGroup) itemView;
        ViewGroup parent = (ViewGroup) bindItem.getParent();
        if (parent != null) {
            parent.removeView(bindItem);
        }
        container.addView(bindItem, 0);
    }
}
