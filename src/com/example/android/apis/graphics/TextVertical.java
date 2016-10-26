package com.example.android.apis.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/10/26 15:20 <br>
 * description: 垂直绘制的文本 <br>
 */
public class TextVertical extends GraphicsActivity {
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }
     private static class SampleView extends View {
         private Paint mPaint;
         private float   mX;
         private float[] mPos;  //字符串文本每一个字符的中间坐标的数组


         public SampleView(Context context) {
             super(context);
         }

         @Override
         protected void onDraw(Canvas canvas) {
             super.onDraw(canvas);
         }
     }
}
