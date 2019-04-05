package com.android.team.moshey.views;


import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created By blackcoder
 * On 05/04/19
 **/
public class MosheyImageView extends AppCompatImageView {
    public MosheyImageView(Context context) {
        super(context);
    }

    public MosheyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MosheyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Unpack given dimens to new aspect ratio
        int threeTwoHeight = MeasureSpec.getSize(widthMeasureSpec) * 2 / 3;
        int threeTwoHeightSpec = MeasureSpec.makeMeasureSpec(threeTwoHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, threeTwoHeightSpec);
    }
}
