package amilawijayarathna.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by amila on 9/10/17.
 */

public class Draw_view extends View implements View.OnTouchListener{
    private int mDotSize;
    private final int MxDotSize=100;
    private final int MinDotSize=5;
    private final int DefaultDotSize = 10;
    private final int Default_Color = Color.GREEN;
    private int mColor;
    private ArrayList<Path> mPaths;
    private ArrayList<Paint>mPaints;
    private Path mPath;
    private Paint mPaint;
    private float mX,mY,mOldX,mOldY;



    public Draw_view(Context context) {
        super(context);
        this.init();
    }

    public Draw_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();

    }

    public Draw_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();

    }
    private void init() {
        mDotSize=DefaultDotSize;
        mColor= Default_Color;
        this.mPaths= new ArrayList<Path>();
        this.mPaints = new ArrayList<Paint>();
        mPath = new Path();
        InitPaint(false);
        this.mX= this.mY= this.mOldX=this.mOldY=(float)0.0;
        this.setOnTouchListener(this);
    }

    private void InitPaint(boolean fill){
        mPath = new Path();
        mPaths.add(mPath);
        mPaint = new Paint();
        mPaints.add(mPaint);
        mPaint.setColor(mColor);
        if(!fill)
                 mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mDotSize);

    }

    public int getDotSize() {
        return mDotSize;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int Color) {
        this.mColor = Color;

    }

    public void changeDotSize(int increment) {
        this.mDotSize += increment;
        this.mDotSize= Math.max(mDotSize,MinDotSize);
        this.mDotSize=Math.min(mDotSize,MxDotSize);

    }

    public void reset() {
        this.init();
        this.invalidate();
  }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0; i<mPaths.size();i++)
        canvas.drawPath(mPaths.get(i),mPaints.get(i));
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mX= motionEvent.getX();
        mY= motionEvent.getY();
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.mPath.addCircle(mX,mY,mDotSize/2, Path.Direction.CW);
                this.InitPaint(false);
                this.mPath.moveTo(mX,mY);
                break;
            case MotionEvent.ACTION_MOVE:
                this.mPath.lineTo(mX,mY);
                break;
            case MotionEvent.ACTION_UP:
                this.InitPaint(true);
                if(mOldX==mX && mOldY==mY){
                    this.mPath.addCircle(mX,mY,mDotSize/2, Path.Direction.CW);
                }
                break;

        }
        this.invalidate();
        mOldX=mX;
        mOldY=mY;

        return true;
    }
}
