package ru.gdg_siberia.city_feature.ui.base.recycler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

abstract public class HeaderFooterRecyclerAdapter<T> extends RecyclerAdapter<T> {

    private static final int VIEW_TYPE_HEADER = -1;
    private static final int VIEW_TYPE_FOOTER = -2;

    private List<View> headers = new ArrayList<>();
    private List<View> footers = new ArrayList<>();

    public HeaderFooterRecyclerAdapter(Context context) {
        super(context);
    }

    public HeaderFooterRecyclerAdapter(Context context, T[] items) {
        super(context, items);
    }

    public HeaderFooterRecyclerAdapter(Context context, List<T> items) {
        super(context, items);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BindableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerAdapter.VIEW_TYPE_BASE_ITEM) {
            return super.onCreateViewHolder(parent, viewType);
        } else if (viewType == VIEW_TYPE_HEADER) {
            return new HeaderFooterViewHolder(getHeaderView());
        } else {
            return new HeaderFooterViewHolder(getFooterView());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BindableViewHolder bindableViewHolder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                ((HeaderFooterViewHolder) bindableViewHolder).bind(headers.get(position));
                break;
            case VIEW_TYPE_FOOTER:
                ((HeaderFooterViewHolder) bindableViewHolder).bind(footers.get(position - headers.size() - super.getItemCount()));
                break;
            default:
                super.onBindViewHolder(bindableViewHolder, position - headers.size());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return headers.size() + super.getItemCount() + footers.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headers.size()) {
            return VIEW_TYPE_HEADER;
        } else if (position >= headers.size() + super.getItemCount()) {
            return VIEW_TYPE_FOOTER;
        } else {
            return super.getItemViewType(position);
        }
    }

    protected View getHeaderView() {
        LinearLayout layout = new LinearLayout(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(lp);
        layout.setOrientation(LinearLayout.VERTICAL);
        return layout;
    }

    protected View getFooterView() {
        LinearLayout layout = new LinearLayout(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(lp);
        layout.setOrientation(LinearLayout.VERTICAL);
        return layout;
    }

    @Override
    public void add(T item) {
        items.add(item);
        notifyItemInserted(headers.size() + items.size() - 1);
    }

    @Override
    public void add(T item, int position) {
        items.add(position, item);
        notifyItemInserted(headers.size() + position);
    }

    @Override
    public void remove(int position) {
        items.remove(position - headers.size());
        notifyItemRemoved(position);
    }

    @Override
    public void remove(T item) {
        final int position = items.indexOf(item);
        items.remove(position);
        final int notifyPosition = position + headers.size();
        notifyItemRemoved(notifyPosition);
    }

    public int getHeadersSize() {
        return headers.size();
    }

    public int getFootersSize() {
        return footers.size();
    }

    public void addHeader(View header) {
        if (header != null && !headers.contains(header)) {
            headers.add(header);
            notifyItemInserted(headers.size() - 1);
        }
    }

    public void removeHeader(View header) {
        if (headers.contains(header)) {
            notifyItemRemoved(headers.indexOf(header));
            headers.remove(header);
        }
    }

    public void addFooter(View footer) {
        if (!footers.contains(footer)) {
            footers.add(footer);
            notifyItemInserted(super.getItemCount() + footers.size() - 1);
        }
    }

    public void removeFooter(View footer) {
        if (footers.contains(footer)) {
            notifyItemRemoved(super.getItemCount() + footers.indexOf(footer));
            footers.remove(footer);
        }
    }
}
