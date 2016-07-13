package com.x7chen.dev.bt_telephone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2015/12/29.
 */
public class FMPointing extends View {

    final int VIEW_PADDING = 10;
    final int START_TEXT_WIDTH = 100;
    final int START_TEXT_HEIGHT = 60;
    final int START_TEXT_START_X = (VIEW_PADDING);
    final int START_TEXT_START_Y = (VIEW_PADDING+START_TEXT_HEIGHT);
    final int END_TEXT_WIDTH = 100;
    final int END_TEXT_HEIGHT = START_TEXT_HEIGHT;
    final int LEVEL_CNT = 65;
    final int LEVEL_SPACE = 15;
    final int LEVEL_HEIGHT = 100;
    final int LEVEL_HEIGHT_MIN = 20;
    final int LEVEL_START_X = (VIEW_PADDING + START_TEXT_WIDTH);
    final int LEVEL_START_Y = VIEW_PADDING;
    final int END_TEXT_START_X = (VIEW_PADDING+(LEVEL_CNT) * LEVEL_SPACE + END_TEXT_WIDTH);
    final int END_TEXT_START_Y = (VIEW_PADDING+END_TEXT_HEIGHT);
    final int POINT_START = LEVEL_START_X;
    final int POINT_END = (LEVEL_START_X + (LEVEL_CNT-1) * LEVEL_SPACE);
    final int POINT_WIDTH = (LEVEL_SPACE * 2);
    final int POINT_HEIGHT = LEVEL_HEIGHT;
    final int POINT_RANGE = (POINT_END - POINT_START);
    final float FREQUENCY_START = 76;
    final float FREQUENCY_END = 108;
    final float FREQUENCY_RANGE = (FREQUENCY_END - FREQUENCY_START);
    float Point = LEVEL_START_X;
    float mLastTouchX = 0;
    float mLastTouchY = 0;

    final int VIEW_WIDTH = (LEVEL_CNT * LEVEL_SPACE + START_TEXT_WIDTH + END_TEXT_WIDTH);
    final int VIEW_HEIGHT = LEVEL_HEIGHT;

    float Frequency = FREQUENCY_START;

    public FMPointing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FMPointing(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FMPointing(Context context) {
        super(context);
    }

    public float getFrequency() {
        return Frequency;
    }

    public void setFrequency(float frequency) {
        Frequency = frequency;
        Point = POINT_START +((Frequency-FREQUENCY_START)/FREQUENCY_RANGE)*POINT_RANGE;
        invalidate();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        //LEVELS
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(4.0f);
        float[] lines = new float[LEVEL_CNT * 4];
        for (int i = 0; i < LEVEL_CNT; i++) {
            lines[i * 4] = LEVEL_START_X + i * LEVEL_SPACE;
            lines[i * 4 + 1] = LEVEL_START_Y + (LEVEL_HEIGHT - LEVEL_HEIGHT_MIN) / 2;
            lines[i * 4 + 2] = lines[i * 4];
            lines[i * 4 + 3] = lines[i * 4 + 1] + LEVEL_HEIGHT_MIN;
        }
        lines[0] = LEVEL_START_X;
        lines[1] = LEVEL_START_Y;
        lines[2] = lines[0];
        lines[3] = lines[1] + LEVEL_HEIGHT;
        lines[(LEVEL_CNT - 1) * 4] = LEVEL_START_X + (LEVEL_CNT - 1) * LEVEL_SPACE;
        lines[(LEVEL_CNT - 1) * 4 + 1] = LEVEL_START_Y;
        lines[(LEVEL_CNT - 1) * 4 + 2] = lines[(LEVEL_CNT - 1) * 4];
        lines[(LEVEL_CNT - 1) * 4 + 3] = lines[(LEVEL_CNT - 1) * 4 + 1] + LEVEL_HEIGHT;
        canvas.drawLines(lines, paint);
        //POINT
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        canvas.drawLine(Point, LEVEL_START_Y + 10, Point, LEVEL_START_Y + LEVEL_HEIGHT / 2, paint);
        Path path = new Path();
        path.moveTo(Point, LEVEL_START_Y + LEVEL_HEIGHT / 2);
        path.lineTo((Point - POINT_WIDTH / 2), LEVEL_START_Y + LEVEL_HEIGHT / 2 + LEVEL_HEIGHT / 4);
        path.lineTo((Point - POINT_WIDTH / 2), LEVEL_START_Y + LEVEL_HEIGHT);
        path.lineTo((Point + POINT_WIDTH / 2), LEVEL_START_Y + LEVEL_HEIGHT);
        path.lineTo((Point + POINT_WIDTH / 2), LEVEL_START_Y + LEVEL_HEIGHT / 2 + LEVEL_HEIGHT / 4);
        path.close();
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);
        paint.setTextSize(40);
        paint.setStrokeWidth(2);
        canvas.drawText("76.0", START_TEXT_START_X, START_TEXT_START_Y, paint);
        canvas.drawText("108.0",END_TEXT_START_X,END_TEXT_START_Y,paint);
//        canvas.drawRect(100, 0, 120, 60, paint);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.getMode(widthMeasureSpec) + VIEW_WIDTH + VIEW_PADDING * 2;
        heightMeasureSpec = MeasureSpec.getMode(heightMeasureSpec) + VIEW_HEIGHT + VIEW_PADDING * 2;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        final int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                Point = x;
//                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastTouchX;
                Point += dx;
                if (Point < POINT_START) {
                    Point = POINT_START;
                }
                if (Point > POINT_END) {
                    Point = POINT_END;
                }
                Frequency = ((Point-POINT_START)/POINT_RANGE)*FREQUENCY_RANGE+ FREQUENCY_START;
                if(mCallBacks!=null){
                    mCallBacks.onFrequencyChanged(Frequency);
                }
                invalidate();
                break;
        }
        mLastTouchX = x;
        mLastTouchY = y;
//        return super.onTouchEvent(event);
        //只有return true 才能捕获ACTION_MOVE
        return true;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
//        super.setOnTouchListener(l);
    }
    CallBacks mCallBacks;
    public void setCallBacks(CallBacks callBacks){
        mCallBacks = callBacks;
    }
    interface CallBacks{
        void onFrequencyChanged(float frequency);
    }

}
