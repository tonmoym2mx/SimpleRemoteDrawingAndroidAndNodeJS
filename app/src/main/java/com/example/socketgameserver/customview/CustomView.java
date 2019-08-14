package com.example.socketgameserver.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomView extends View {
    private List<XY_> xyList = new ArrayList<>();
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void addPoint(XY_ xy_){
        xyList.add(xy_);
        invalidate();
    }
    public void clear(){
        xyList.clear();
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        if(xyList.size()>0) {
            for (int i = 0; i < xyList.size(); i++) {
                if(i >0) {
                    XY_ firstXY = xyList.get(i - 1);
                    XY_ secondXY = xyList.get(i);
                    canvas.drawLine(firstXY.getX_(), firstXY.getY_(),
                            secondXY.getX_(), secondXY.getY_(), paint);
                }
            }
        }

    }
}
