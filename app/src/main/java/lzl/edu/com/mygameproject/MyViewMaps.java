package lzl.edu.com.mygameproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

import view.MyView;

/**
 * Created by admin on 2015/11/25.
 */
public class MyViewMaps {
    int n = MainActivity.N;
    MyView btnMaps[][] = new MyView[n][n];
    private TextView mScore,mHighScore;
    int sum=0;
    SharedPreferences preferences;
    /**
     * 将button按钮添加到数组中去。
     * @param i  横坐标
     * @param j  纵坐标
     * @param btn  按钮
     */
    public void addButton(int i,int j,MyView btn){
        btnMaps[i][j] = btn;
    }

    /**
     * 初始化 游戏界面
     */
    public void initMaps(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                btnMaps[i][j].setNumber(0);
            }
        }
        addRandomButton();
    }

    public TextView getmScore() {
        return mScore;
    }

    public void setmScore(TextView mScore) {
        this.mScore = mScore;
    }

    public TextView getmHighScore() {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("nowScore",Integer.parseInt(mHighScore.getText().toString()));
        editor.commit();
        return mHighScore;
    }

    public void setmHighScore(TextView mHighScore) {
        this.mHighScore = mHighScore;
         preferences = mHighScore.getContext().getSharedPreferences("highScore.txt"
                , Context.MODE_PRIVATE);
    }

    /**
     * 设置分数
     * @param score
     */
    public void setScore(int score){
        mScore.setText(""+score);
    }
    private void setHighScore(){
        int highScore = Integer.valueOf(getmScore().getText().toString());
        Log.i("分数。。。。。","highScore...."+highScore);
        int nowScore = preferences.getInt("nowScore",0);
        if(nowScore < highScore){  //如果当前数据库中的分数小于现在的最高分数，将这个分数设置到界面上
            mHighScore.setText(""+highScore);
        }
    }
    //实现游戏的算法

    /**
     * 置换按钮中的数据的方法
     * @param btn1  第一个按钮
     * @param btn2  第二个按钮
     */
    private void swapBtn(MyView btn1,MyView btn2){
        int num = btn1.getNumber();
        btn1.setNumber(btn2.getNumber());
        btn2.setNumber(num);
    }

    /**
     * 判断滑动方向完成相应的操作
     * @param direction  滑动的方向
     * @return
     */
public boolean slideDirection(int direction){
    switch (direction){
        case Direction.SLIDE_DOWN:  //向下滑动
            downRemoveBlock();
            down();
            if(isFull()){
                return true;
            }else{
                addRandomButton();
            }
            break;
        case Direction.SLIDE_UP:  //向上滑动
            upRemoveBlock();
            up();
            if(isFull()){   //如果全为非0数，说明游戏结束
                return true;
            }else{
                addRandomButton();
            }
            break;
        case Direction.SLIDE_LEFT:  //向左滑动
            leftRemoveBlock();
            left();
            if(isFull()){
                return true;
            }else{
                addRandomButton();
            }
            break;
        case Direction.SLIDE_RIGHT:  //向右滑动
            rightRemoveBlock();
            right();
            if(isFull()){
                return true;
            }else{
                addRandomButton();
            }
            break;
        default:
            break;
    }
    return false;
}

    /**
     * 判断矩阵是否全是非0的数值
     * @ return true 说明已经充满，游戏结束
     *      false  游戏可以继续
     */
    private boolean isFull(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(btnMaps[i][j].getNumber() == 0){ //这里还有bug,不光都为0时就游戏结束了。
                    return false;
                }
            }
        }
     return true;
    }

    /**
     * 随机的产生一个button按钮
     * @return
     */
    private void addRandomButton(){
        Random random = new Random();
        //随机的产生一个横纵坐标
        int x = random.nextInt(n);
        int y = random.nextInt(n);
        int number = random.nextInt(20);
        //按照产生2的几率占90%，产生4的几率为10%的比例产生随机数
        if(number == 0||number == 1){
            number =4;
        }else {
            number = 2;
        }
        //当btnMaps[x][y]为0，再次产生随机的坐标，直到不是0为止
        while(btnMaps[x][y].getNumber() != 0){
            x = random.nextInt(n);
            y = random.nextInt(n);
        }
        //把这个随机数放到btn中
        btnMaps[x][y].setNumber(number);
    }
    /**
     * 向上移动的操作
     */
    private void up(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                int t1 = btnMaps[j][i].getNumber();
                int t2 = btnMaps[j+1][i].getNumber();
                if(t1==t2&&t1!=0){
                    int total = t1+t2;  //t1和t2相加
                    sum += total;
                    btnMaps[j+1][i].setNumber(0);
                    btnMaps[j][i].setNumber(total);
                    setScore(sum);
                    setHighScore();
                    upRemoveBlock();
                }
            }
        }
    }
    //消除向上滑动的“0”方块
    private void upRemoveBlock(){
        for(int i=0;i<n;i++){
            for(int j=1;j<n;j++){
                int k=j;
                while(k-1>=0&&btnMaps[k-1][i].getNumber() == 0){
                    swapBtn(btnMaps[k-1][i],btnMaps[k][i]);
                    k--;
                }
            }
        }
    }

    /**
     * 向下滑动的操作
     */
    private void down(){
        for(int i=0;i<n;i++){
            for(int j=n-1;j>0;j--){
                int t1 = btnMaps[j][i].getNumber();
                int t2 = btnMaps[j-1][i].getNumber();
                if(t1==t2&&t1!=0){
                    int total = t1+t2;
                    sum += total;
                    btnMaps[j][i].setNumber(total);
                    btnMaps[j-1][i].setNumber(0);
                    setScore(sum);
                    setHighScore();

                    downRemoveBlock();
                }
            }
        }
    }
    //消除向下滑动的“0”方块
    private void downRemoveBlock(){
        for(int i=0;i<n;i++){
            for(int j=n-2;j>=0;j--){
                int k=j;
                while(k+1<=n-1&&btnMaps[k+1][i].getNumber() == 0){
                    swapBtn(btnMaps[k+1][i],btnMaps[k][i]);
                    k++;
                }
            }
        }
    }
    private void left(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                int t1= btnMaps[i][j].getNumber();
                int t2 = btnMaps[i][j+1].getNumber();
                if(t1==t2&&t1!=0){   //如果两数相同，那么将数值相加到最左边的数据上面。并将后面的数据置0
                    int total = t1+t2;
                    sum += total;
                    btnMaps[i][j].setNumber(total);
                    btnMaps[i][j+1].setNumber(0);
                    setScore(sum);
                    setHighScore();

                    leftRemoveBlock();
                }
            }
        }
    }
    //消除向左移动的“0”方块
    private void leftRemoveBlock(){
        for(int i=0;i<n;i++){
            for(int j=1;j<=n-1;j++){
                int k=j;
                while(k-1>=0&&btnMaps[i][k-1].getNumber() == 0){  //向左滑动时，有为0的元素时就置换
                   swapBtn(btnMaps[i][k-1],btnMaps[i][k]);
                    k--;
                }
            }
        }
    }
    private void right(){
        for(int i=0;i<n;i++){
            for(int j=n-1;j>0;j--){
                int t1 = btnMaps[i][j].getNumber();
                int t2 = btnMaps[i][j-1].getNumber();
                if(t1==t2&&t1!=0){
                    int total = t1+t2;
                    sum += total;
                    btnMaps[i][j].setNumber(total);
                    btnMaps[i][j-1].setNumber(0);
                    setScore(sum);
                    setHighScore();

                    rightRemoveBlock();
                }
            }
        }
    }
    //消除向右移动的“0”方块
    private void rightRemoveBlock(){
        for(int i=0;i<n;i++){
            for(int j=n-2;j>=0;j--){
                int k=j;
                while(k+1<=n-1&&btnMaps[i][k+1].getNumber() == 0){  //向左滑动时，有为0的元素时就置换
                    swapBtn(btnMaps[i][k+1],btnMaps[i][k]);
                    k++;
                }
            }
        }
    }
}
