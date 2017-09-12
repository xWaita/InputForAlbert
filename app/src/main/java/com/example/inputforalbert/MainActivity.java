package com.example.inputforalbert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int actionPerformed = e.getAction();
        boolean trigger = true;
        uf(false)
        switch (actionPerformed) {
            case MotionEvent.ACTION_POINTER_UP:{
                counter++;
                if (counter > 9) {
                    counter = 0;
                }
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(String.valueOf(counter));
                trigger = true;
                break;
            }
            case MotionEvent.ACTION_UP:{
                counter++;
                if (counter > 9) {
                    counter = 0;
                }
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(String.valueOf(counter));
                trigger = true;
                break;
            }
        }

        return trigger;
    }
    */
    @Override
    public boolean onTouchEvent (MotionEvent e) {
        counter += e.getPointerCount();
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(counter));
        return true;
    }

}
