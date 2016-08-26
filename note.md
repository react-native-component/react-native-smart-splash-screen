
* 要注意 ios 使用默认实现的addLoadingView的方法, 在主View之前增加一个view来实现splashscreen
  要注意 android 使用新增一个Dialog的方式来实现splashscreen

* 要注意 android 代码获取图片资源时(无文件后缀名), png与jpg都可以获取到
  但是在 ios 代码获取图片资源时(无文件后缀名), 只能获取到png

* 要注意 android 使用了bitmap操作图片进行了裁剪,
  为使launchScreen时设置的背景图与splashScreen的图片展现一致(android的顶部的statusBar与底部的navigationBar的高度会影响Dialog)