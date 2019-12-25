package com.magody.coverflowwithviewpager.model.coverflow;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class FlowPageViewLinearLayout extends LinearLayout {

    private float scale = FlowPageViewAdapter.BIG_SCALE;
    public FlowPageViewLinearLayout(Context context, AttributeSet attrs) {

        super(context, attrs);

    }
    public FlowPageViewLinearLayout(Context context) {

        super(context);

    }
    public void setScaleBoth(float scale) {

        this.scale = scale;

        this.invalidate();

    }
    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        // The main mechanism to display scale animation, you can customize it as your needs

        int w = this.getWidth();

        int h = this.getHeight();

        /* IN canvas.scale ITS HAPPENING THIS
        * Scale takes this arguments
        * sx : scale in x
        * sy : scale in y
        * scale(sx, sy);
        * px : translate in x
        * py : translate in y
        * translate(px, py);
        *
        * In this way:
        *
        * translate(px, py);
        * scale(sx, sy);
        * translate(-px, -py);
        * */

        //if we dont divide w, the images on the sides will be too far apart. w/2 puts it in the center of the images in the sides
        //if we dont divide h, the images on the sides will be very down. h/2 puts it in the center

        //the scale changes when we scroll
        canvas.scale(scale, scale, w/2, h/2);


        //canvas.translate(w/2, h/2);
        //canvas.scale(scale, scale);
        //canvas.scale(scale, scale);
        //canvas.translate(w/2, h/2);
        //canvas.translate(-w/2, -h/2);
        //canvas.scale(scale, scale, w/10, h/2);

    }


}
