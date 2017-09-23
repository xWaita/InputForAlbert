package com.example.inputforalbert;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.TextView;
import android.view.GestureDetector;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

import static android.view.MotionEvent.ACTION_UP;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static int pinLimit = 12;
    private static int scrollThreshhold = 300;
    private static int tapThreshhold = 50;

    // current digit
    private static int digit = 0;
    // last number of pointers on screen.
    private static int lastCount = 0;
    // TextView for current digit
    private TextView digitView;
    // TextView for pin
    private TextView pinView;
    // ArrayList for pin
    private ArrayList<String> pin;

    //Initializer stuff for methods
    private GestureDetector gestureStuff;
    private Vibrator v;

    //variables for scrolling
    private float dx;
    private float dy;
    private boolean isScrolling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        digitView = (TextView) findViewById(R.id.digitView);
        pinView = (TextView) findViewById(R.id.pinView);
        this.gestureStuff = new GestureDetector(this,this);
        pin = new ArrayList<String>();
        v = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        isScrolling = false;
    }

    @Override
    public boolean onTouchEvent (MotionEvent e) {
        if(!this.gestureStuff.onTouchEvent(e)) {
            if(e.getAction() == ACTION_UP && isScrolling) {
                float absX = (dx > 0) ? dx : -dx;
                float absY = (dy > 0) ? dy : -dy;
                if(absX > absY) {
                    if (dx > scrollThreshhold) {
                        setDigit("RIGHT");
                    } else if (dx < -scrollThreshhold) {
                        setDigit("LEFT");
                    }
                } else {
                    if(dy < -scrollThreshhold) {
                        setDigit("UP");
                    } else if (dy > scrollThreshhold) {
                        setDigit("DOWN");
                    }
                }
                isScrolling = false;
            } else {
                if (e.getPointerCount() < lastCount) {
                    digit += (lastCount - e.getPointerCount());
                    v.vibrate(10);
                }
                if (e.getAction() == ACTION_UP) {
                    digit++;
                    v.vibrate(20);
                }
                if (digit > 9) {
                    digit = 9;
                }
                digitView.setText(String.valueOf(digit));
                lastCount = e.getPointerCount();

                PulsatorLayout pulsator = (PulsatorLayout) findViewById(R.id.pulsator);
                pulsator.start();
            }
        }

        return true;
    }

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
        /*
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        setDigit("RIGHT");
                    } else {
                        setDigit("LEFT");
                    }
                    result = true;
                }
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    setDigit("DOWN");
                } else {
                    digitView.setText("UP");
                }
                result = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
        */
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    private static final double DEADZONE_ANGLE = 0.57735026919;

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        dx = motionEvent1.getX(0) - motionEvent.getX(0);
        dy = motionEvent1.getY(0) - motionEvent.getY(0);
        isScrolling = true;
        return true;
    }

    private void setDigit(String swipe) {

        int currentPin = pin.size();
        if(swipe.equals("RIGHT")) {
            if(currentPin >= pinLimit) return;
            pin.add(String.valueOf(digit));
            digit = 0;
        } else if(swipe.equals("LEFT")){
            digit = 0;
        } else if(swipe.equals("DOWN")){
            pin.clear();
        }
        digitView.setText(String.valueOf(digit));
        pinView.setText(TextUtils.join(" ", pin));

        v.vibrate(80);
    }
    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }
}
