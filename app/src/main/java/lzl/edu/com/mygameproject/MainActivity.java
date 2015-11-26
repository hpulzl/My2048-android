package lzl.edu.com.mygameproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import view.MyView;

import static lzl.edu.com.mygameproject.R.id.reBackBtn;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private TextView mScoreTv;
    private TextView mHighScoreTv;
    private GridLayout mGridLayout;
    private Button mRestartBtn,mRebackBtn;
    private LayoutInflater inflater;
    private MyView mBtn;
    public static int N = 4;   //表示矩阵的大小。后期可以根据具体的设置来进行更改
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    MyViewMaps maps = new MyViewMaps();
    public static final String Tag = "Mainactivity....";
    SharedPreferences mPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(this);
        mPreference = getSharedPreferences("highScore.txt",MODE_PRIVATE);
        initView();
    }
    private void initView(){
        mGridLayout = (GridLayout) findViewById(R.id.myGridLayout);
        mHighScoreTv = (TextView) findViewById(R.id.highScoreTv); //游戏的最高分
        mScoreTv = (TextView) findViewById(R.id.scoreTv);   //游戏分数
        mRebackBtn = (Button)findViewById(reBackBtn);
        mRestartBtn = (Button) findViewById(R.id.reStartBtn);

        //获取最高分数
        int highScore = mPreference.getInt("nowScore",0);

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
       LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.myview_item, null);
            mBtn = (MyView) layout.findViewById(R.id.myViewItem);
                //指定GridLayout所开始的行
                GridLayout.Spec rowSpec = GridLayout.spec(i);
                //指定GridLayout 所开始的列
                GridLayout.Spec columnSpec = GridLayout.spec(j);
                //指定GridLayout 的行和列
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,columnSpec);
                //将布局加到 GridLayout中去
               mGridLayout.addView(layout,params);
                maps.addButton(i,j,mBtn);
            }
        }
        //初始化游戏的分数
        mHighScoreTv.setText(""+highScore);
        mScoreTv.setText("" + 0);
        maps.setmScore(mScoreTv);
        maps.setmHighScore(mHighScoreTv);
        maps.initMaps();

        //设置点击事件
        mRestartBtn.setOnClickListener(this);
        mRebackBtn.setOnClickListener(this);
    }
    /**
     * 通过系统提供的点击方法，获取用户手势移动的横纵坐标
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
       int action = ev.getAction();
        if(action == MotionEvent.ACTION_UP){   //如果是手势抬起时的动作，记录其离开的x、y坐标
            endX = ev.getX();
            endY = ev.getY();
        }else if(action == MotionEvent.ACTION_DOWN){  //如果是手势按下的动作，记录其按下时的x、y坐标.并触发相应的事件
            startX = ev.getX();
            startY = ev.getY();
            int direction = getSlideDirection(startX,startY,endX,endY);
            boolean gameOver = maps.slideDirection(direction);
            if (gameOver){   //如果为true，表示游戏已结束
                Toast.makeText(this,"游戏结束您当前对高分是："+maps.getmHighScore().getText(),Toast.LENGTH_SHORT).show();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     *通过x，y的横纵坐标差来获取用户滑动的角度。
     * 这个角度可以判断用户的滑动方向
     * @return 返回角度
     */
    private double getSlideAngle(float dx,float dy){
        return (float)Math.atan2(dy, dx) *180 /Math.PI;
    }

    /**
     *判断用户的滑动方向。
     * @param startX   手势按下时的x坐标
     * @param startY   手势按下时的Y坐标
     * @param endX    手势抬起时的x坐标
     * @param endY   手势抬起时的Y坐标
     * @return
     */
    private int getSlideDirection(float startX,float startY,float endX,float endY){
        float dx = startX - endX;
        float dy = startY - endY;
        if(Math.abs(dx)<2 && Math.abs(dy)<2){  //如果滑动的距离太短，设置为没有滑动
            return Direction.SLIDE_NONE;
        }
        double angle = getSlideAngle(dx,dy);   //获取返回的角度
        Log.i(Tag,"角度是。。。"+angle);
        if(angle >=-45 &&angle<45 ){      //返回向右滑动的方法
            return Direction.SLIDE_LEFT;
        }else if(angle>=45&&angle<135){  //向上滑动
            return Direction.SLIDE_UP;
        }else if(angle>=-135 && angle<-45){
            return Direction.SLIDE_DOWN;
        }else if((angle >= 135 && angle <= 180)
                || (angle >= -180 && angle < -135)){
            return Direction.SLIDE_RIGHT;
        }
        return Direction.SLIDE_NONE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reBackBtn:
                Toast.makeText(this,"点击我了。。。",Toast.LENGTH_SHORT).show();
                break;
            case R.id.reStartBtn:
                maps.initMaps();
                break;
            default:
                break;
        }
    }
}
