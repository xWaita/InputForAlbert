package com.example.inputforalbert;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Tutorial5 extends AppCompatActivity {

    private ImageButton nextButton;
    private ImageButton backButton;

    private ImageButton playSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial5);

        final MediaPlayer tutorialScript = MediaPlayer.create(this, R.raw.step_4);

        nextButton = (ImageButton) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tutorialScript.release();
                Intent nextTutorial = new Intent(getApplicationContext(), Tutorial6.class);
                startActivity(nextTutorial);
            }
        });

        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tutorialScript.release();
                Intent prevTutorial = new Intent(getApplicationContext(), Tutorial4.class);
                startActivity(prevTutorial);
            }
        });

        playSound = (ImageButton) findViewById(R.id.soundButton);
        playSound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tutorialScript.start();
            }
        });
    }
}
