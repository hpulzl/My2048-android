package lzl.edu.com.mygameproject;

/**
 * Created by admin on 2015/11/25.
 */
public interface Direction {
    //设置向上、向下、向左、向右、不滑动五种状态
    static final int SLIDE_UP = 1;   //向上
    static final  int SLIDE_DOWN = 2; //向下
    static final  int SLIDE_LEFT = 3; //向左
    static final  int SLIDE_RIGHT = 4; // 向右
    static final  int SLIDE_NONE = 0;  // 不滑动
}
