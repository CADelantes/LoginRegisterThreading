package com.example.loginregisterthreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Threading extends AppCompatActivity {

    TextView increase, decrease;
    Button increaseBtn, decreaseBtn;
    int increaseNum, decreaseNum;

    private volatile boolean stopThreadFlag = false;

//    private Handler mainHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threading);

        increase = findViewById(R.id.IncreaseNumberTextView);
        decrease = findViewById(R.id.DecreaseNumberTextView);
        increaseBtn = findViewById(R.id.IncreaseButton);
        decreaseBtn = findViewById(R.id.DecreaseButton);
        increaseNum = 1;
        decreaseNum = 10;

        increaseBtn.setOnClickListener(v -> {
            startThread(10);
            increase.setText(String.valueOf(increaseNum));
            increaseNum++;
            if (increaseNum == 10) {
                increaseNum = 1;
            }
        });

        decreaseBtn.setOnClickListener(v -> {
            stopThread();
            decrease.setText(String.valueOf(decreaseNum));
            decreaseNum--;
            if (decreaseNum == 1) {
                decreaseNum = 10;
            }
        });

    }

    public void startThread(int seconds) {

        for (int i = 0; i < seconds; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        GraduationThread thread = new GraduationThread(10);
        thread.start();
    }

    public void stopThread() {
        stopThreadFlag = true;
    }

    class GraduationThread extends Thread
    {
        int seconds;

        //Non-Default Constructor
        GraduationThread(int seconds){
            this.seconds = seconds;
        }
        @Override
        public void run(){
            for(int i =0; i< seconds; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}