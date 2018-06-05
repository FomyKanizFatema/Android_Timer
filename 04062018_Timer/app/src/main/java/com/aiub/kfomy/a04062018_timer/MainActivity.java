package com.aiub.kfomy.a04062018_timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView timerText;
    Button btnController;
    boolean counterIsActive;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(0);

        timerText=findViewById(R.id.time);
        btnController=findViewById(R.id.btn);
        counterIsActive=false;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controllTimer(view);
                         }
        });
    }

    public void controllTimer(View view){
        if(counterIsActive==false) {
            counterIsActive=true;
            btnController.setText("Stop");
            seekBar.setEnabled(false);
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long leftMilisecondsToFinish) {
                    updateTimer((int) leftMilisecondsToFinish / 1000);
                }

                @Override
                public void onFinish() {
                    timerText.setText("00:00");
                    Toast.makeText(MainActivity.this, "Times Up", Toast.LENGTH_LONG).show();
                }
            }.start();
        }
        else if(counterIsActive==true){
            btnController.setText("GO !");
            seekBar.setProgress(0);
            timerText.setText("00:00");
            countDownTimer.cancel();
            seekBar.setEnabled(true);
            counterIsActive=false;
        }
    }
    public void updateTimer(int secondsLeft){
        int minutes=(int)secondsLeft/60;
        int seconds=secondsLeft-minutes*60;
        String secondString=Integer.toString(seconds);
        String minuteString=Integer.toString(minutes);
        if(seconds<=9){
            secondString="0"+secondString;
        }
        if(minutes<=9){
            minuteString="0"+minuteString;
        }

        timerText.setText(minuteString+":"+secondString);
    }
}
