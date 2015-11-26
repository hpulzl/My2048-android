package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import lzl.edu.com.mygameproject.Direction;
import lzl.edu.com.mygameproject.MyViewMaps;

/**
 * Created by admin on 2015/11/26.
 */
public class MyView extends View {
    private Paint paint ;//定义画笔
    private int number;  //设置方块上面的数据
    private String numberVal;//设置String类型的数据
    private Rect rect;  //设置绘制的区域
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    MyViewMaps maps = new MyViewMaps();
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        numberVal = number+"";
        paint.setTextSize(50.0f);  //设置文本的大小
        rect = new Rect();
        paint.getTextBounds(numberVal, 0, numberVal.length(), rect);
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String mBgColor = "";
        switch (number)
        {
            case 0:
                mBgColor = "#CCC0B3";
                break;
            case 2:
                mBgColor = "#EEE4DA";
                break;
            case 4:
                mBgColor = "#EDE0C8";
                break;
            case 8:
                mBgColor = "#F2B179";// #F2B179
                break;
            case 16:
                mBgColor = "#F49563";
                break;
            case 32:
                mBgColor = "#F5794D";
                break;
            case 64:
                mBgColor = "#F55D37";
                break;
            case 128:
                mBgColor = "#EEE863";
                break;
            case 256:
                mBgColor = "#EDB04D";
                break;
            case 512:
                mBgColor = "#ECB04D";
                break;
            case 1024:
                mBgColor = "#EB9437";
                break;
            case 2048:
                mBgColor = "#EA7821";
                break;
            default:
                mBgColor = "#EA7821";
                break;
        }
        paint.setColor(Color.parseColor(mBgColor));
        paint.setStyle(Paint.Style.FILL);
        RectF oval = new RectF(0,0,getWidth(),getHeight());   //绘制圆角矩形
        canvas.drawRoundRect(oval, 12, 12, paint);
        if (number!=0){
            drawText(canvas);
        }
    }
    private void drawText(Canvas canvas){
        paint.setColor(Color.WHITE);
        float x = (getWidth()-rect.width())/2;
        float y = (getHeight()+rect.height())/2;
        canvas.drawText(numberVal,x,y,paint);
    }
}
