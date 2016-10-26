/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.view;

import com.example.android.apis.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

public class DragAndDropDemo extends Activity {
    TextView mResultText;
    DraggableDot mHiddenDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_layout);

        TextView text = (TextView) findViewById(R.id.drag_text);
        DraggableDot dot = (DraggableDot) findViewById(R.id.drag_dot_1);
        dot.setReportView(text);
        dot = (DraggableDot) findViewById(R.id.drag_dot_2);
        dot.setReportView(text);
        dot = (DraggableDot) findViewById(R.id.drag_dot_3);
        dot.setReportView(text);

        mHiddenDot = (DraggableDot) findViewById(R.id.drag_dot_hidden);
        mHiddenDot.setReportView(text);

        mResultText = (TextView) findViewById(R.id.drag_result_text);
        mResultText.setOnDragListener(new View.OnDragListener() {
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch (action) {
                    //开始抓取
                    case DragEvent.ACTION_DRAG_STARTED: {
                        //当”在空中的时候“调出第四个可抓取的点，注意、它是恰当通知关于持续拖拽的，同时亮起来，表示它能处理当前内容
                        // Bring up a fourth draggable dot on the fly. Note that it is properly notified about the ongoing drag, and lights up
                        // to indicate that it can handle the current content.
                        mHiddenDot.setVisibility(View.VISIBLE);
                    } break;
                    //抓取结束
                    case DragEvent.ACTION_DRAG_ENDED: {
                        // Hide the surprise again
                        mHiddenDot.setVisibility(View.INVISIBLE);
                        // Report the drop/no-drop result to the user
                        // getResult()只返回动作类型是：ACTION_DRAG_ENDED
                        // 返回值取决于用户释放了拖动阴影后发生了什么
                        // 如果用户在一个能够接收一个drop时间的View上释放drop的shadow，系统会发送一个Action_Drop的事件给目标View对象的Drop时间监听器，
                        // 若这个监听器返回true/false，那getResult()也会返回true/false
                        // 注意: 如果没有发送ACTION_DROP，getResult()也会返回false,例如：使用者将drag的阴影释放到app的区域之外，
                        // 这种情况下系统发出ACTION_DRAG_ENDED（抓取结束）为当前操作，但并没有发送ACTION_DROP（丢）

                        // 丢成功了，drag event listener return true，则返回true。没有丢或者丢失败了，返回false(包括，系统没有抓的开始和抓取结束的相应，或者)
                        final boolean dropped = event.getResult();
                        mResultText.setText(dropped ? "Dropped!" : "No drop");
                    } break;
                }
                return false;
            }
        });
    }
}