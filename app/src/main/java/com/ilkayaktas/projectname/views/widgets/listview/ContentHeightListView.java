package com.ilkayaktas.projectname.views.widgets.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by iaktas on 06.06.2017.
 */

public class ContentHeightListView extends ListView {

    public ContentHeightListView(Context context) {
        super(context);
    }

    public ContentHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
