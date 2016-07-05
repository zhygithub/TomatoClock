package network.scau.com.tomatoclock.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import network.scau.com.tomatoclock.R;

/**
 * Created by ZhengHy on 2016/7/5 0005.
 */

public class RecycleViewDivider extends RecyclerView.ItemDecoration {

    public enum Type {
        VERTICAL, HORIZONTAL, ALL
    }

    private Type type;//分割线类型
    private int dividerSize = 10;//分割线尺寸


    public RecycleViewDivider(RecycleViewDivider.Type type, int dividerSize) {
        this.type = type;
        this.dividerSize = dividerSize;
    }


    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();

        switch (type) {
            case ALL:
                if (itemPosition % spanCount == 0) {//第一列
                    if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
                        outRect.set(0, 0, dividerSize / 2, 0);
                    } else {
                        outRect.set(0, 0, dividerSize / 2, dividerSize);
                    }
                } else if (itemPosition % spanCount == spanCount - 1) {//最后一列
                    if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
                        outRect.set(dividerSize / 2, 0, 0, 0);
                    } else {
                        outRect.set(dividerSize / 2, 0, 0, dividerSize);
                    }
                } else {//中间列
                    if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
                        outRect.set(dividerSize / 2, 0, dividerSize / 2, 0);
                    } else {
                        outRect.set(dividerSize / 2, 0, dividerSize / 2, dividerSize);
                    }
                }
                break;
            case VERTICAL:
                if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
                    outRect.set(0, 0, 0, 0);
                } else {
                    outRect.set(0, 0, 0, dividerSize);
                }
                break;
            case HORIZONTAL:
                if (isLastColum(parent, itemPosition, spanCount, childCount)) {
                    outRect.set(0, 0, 0, 0);
                } else {
                    outRect.set(0, 0, dividerSize, 0);
                }
                break;
        }
    }

    // 是否是最后一列
    private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)
                return true;
        } else {
            if (pos == childCount - 1)
                return true;
        }
        return false;
    }

    // 是否是最后一行
    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)
                return true;
        } else {
            if (pos == childCount - 1)
                return true;
        }
        return false;
    }


    //返回列数
    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return -1;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        c.drawColor(parent.getContext().getColor(R.color.colorBorder));
        super.onDraw(c, parent, state);
    }
}
