2015-11-16：从今天开始设计一款2048的小游戏。大概三天左右完成
1、先弄明白2048小游戏的算法过程。
2、完成2048主要代码块，
3、迭代完善2048的所有内容。
=================================================设计步骤及思想=======================================
1、设置游戏主界面
  1.1 游戏界面使用GridLayout布局。这个没有使用过，可以现在网上看看有没有相似的应用使用可以借鉴一下。（今天晚上的任务）
  1.2 游戏界面的相关内容：
  1.2.1 颜色设置：
   小方块的颜色设置为如下规则：
   ---| 数字为2,4,8,16,32,64,128,256,512,1024,2048的颜色各不相同
   ---| 布局基本上与那个图片类似
2、实现游戏算法
  ---| 
  ---| 需要一个产生随机数的方法。用于滑动方块时产生一个随机的方块，该方块只能是
  2或4。
  ---| 判断方块滑动：
    首先滑动的规则，尽量堆叠在一块，遇到障碍物，会进行排队的规则来堆积。
    ---| 上滑
	      
	---| 下滑
	---| 左滑
	---| 右滑
3、设置游戏分数
4、检查结束
---------------------------------------------实现下一步内容-----------------------------------------------
1、实现判断手机的滑动方向，通过手势的滑动来判断用户是上滑、下滑、左滑、右滑、不滑动着五种状态。
2、手势的滑动设置为接口，这个是按照网上的说法做的，不太理解是什么意思，但是照着做的话不会有害处。

//判断手势滑动的方法。
--| 1、手势按下时的x、y坐标(startX,startY)与手势抬起时的坐标(endX,endY)利用这四个点以及角度来判断手势的滑动
//手势滑动判断完成之后，要根据滑动的方向实现相应的操作了。建立一个BtnMaps的类，里面存放N*N的button按钮，并携带
数据。
完成算法之后，需要做以下的方法
---|完成产生随机的button按钮（数据为2或者4）。
---|完成游戏结束时的判断
--------------------------------------------11月26号完结-----------------------------------
---|通过自定义View来实现button数值改变时，颜色也随着改变的功能。
---| 设置最高分，最开始---最高分就是你当前的分数，然后将当前分数保存到本地数据库中（用sherePreferences来保存）。
   然后每次使用设置最高分时，都要与之前的最高分进行比较，如果比之前最高分高，那么设置，否则不用改变
