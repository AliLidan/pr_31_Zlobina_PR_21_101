package com.example.pr_31_zlobina_pr_21_101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    View view;
    int i;
    TextView tvTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        view = findViewById(R.id.main_layout);

        OnSwipeTouchListener btn1 = new OnSwipeTouchListener(this);

        i = 1;
        tvTxt = (TextView) findViewById(R.id.tvTxt);
        tvTxt.setText("" + i);

        view.setOnTouchListener(btn1);
    }


    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }                                   //gestureDetector - это объект класса GestureDetector, который используется для определения жестов
                                            // внутренний класс, расширяющий GestureDetector.SimpleOnGestureListener
        public void onSwipeLeft() {
            i++;
            tvTxt.setText(String.valueOf(i));

        }

        public void onSwipeRight() {
            i--;
            tvTxt.setText(String.valueOf(i));
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }                                       /*   предназначен для обработки событий касания (MotionEvent)  View.
                                                     В нем вызывается метод onTouchEvent() объекта gestureDetector с передачей события event.*/

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }

}