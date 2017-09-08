package ru.gdg_siberia.instant_app_tutorial.ui.base.recycler;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.gdg_siberia.instant_app_tutorial.R;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private int offset;
    private Drawable divider;

    public DividerItemDecoration(Context context) {
        this(context, 0);
    }

    public DividerItemDecoration(Context context, @DimenRes int paddingResId) {
        this.divider = ContextCompat.getDrawable(context, R.drawable.divider_gray);
        try {
            this.offset = context.getResources().getDimensionPixelSize(paddingResId);
        } catch (Resources.NotFoundException e) {
            this.offset = 0;
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = divider.getIntrinsicHeight();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left + offset, top, right, bottom);
            divider.draw(c);
        }
    }
}