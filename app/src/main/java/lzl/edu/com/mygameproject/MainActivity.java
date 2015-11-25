package lzl.edu.com.mygameproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private GridLayout mGridLayout;
    private LayoutInflater inflater;
    private Button mBtn;
    private final int N = 4;   //表示矩阵的大小。后期可以根据具体的设置来进行更改
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(this);
        initView();
    }
    private void initView(){
        mGridLayout = (GridLayout) findViewById(R.id.myGridLayout);
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
       LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.mygrid_item, null);
            mBtn = (Button) layout.findViewById(R.id.mBtnElement);
                //指定GridLayout所开始的行
                GridLayout.Spec rowSpec = GridLayout.spec(i);
                //指定GridLayout 所开始的列
                GridLayout.Spec columnSpec = GridLayout.spec(j);
                //指定GridLayout 的行和列
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,columnSpec);
                //将布局加到 GridLayout中去
               mGridLayout.addView(layout,params);
            }
        }
    }
}
