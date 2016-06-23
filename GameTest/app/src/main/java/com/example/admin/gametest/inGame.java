package com.example.admin.gametest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class inGame extends AppCompatActivity {
    private ProgressBar progressBar;
    private int value, score;
    private float downx, downy, upx, upy;
    private String type;
    private int progress;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(100);
        progressBar.setMax(100);
        img = (ImageView) findViewById(R.id.img);
        play();
        new MyThread().start();
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        downx = event.getX();
                        downy = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        upx = event.getX();
                        upy = event.getY();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        MoveACtion();
        calculator();
        TextView text = (TextView) findViewById(R.id.score);
        text.setText("score: "+score);
    }

    class MyThread extends Thread{

        @Override
        public void run() {
            progress = 100;
            for(int i=0;i<100;i++) {
                progress = progress-1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setProgress(progress);
            }
        }
    }
    public int play(){
        value = (int) (Math.random() * 10);
            if(value<4) {
                img.setImageDrawable(getResources().getDrawable(R.drawable.uparrow));
                return value;
            }
            if(value <6) {
                img.setImageDrawable(getResources().getDrawable(R.drawable.downarrow));
                return value;
            }
            if(value<8) {
                img.setImageDrawable(getResources().getDrawable(R.drawable.leftarrow));
                return value;
            }
            if(value<11) {
                img.setImageDrawable(getResources().getDrawable(R.drawable.rightarrow));
                return value;
            }
        return value;
    }

    public void MoveACtion(){
        type = "";
        if(downx < upx){
            type = "right";
        }
        if (downx > upx ){
            type = "left";
        }
        if (downy < upy ){
            type = "down";
        }
        if(downy>upy){
            type = "up";
        }
    }

    public void calculator(){
        while(progress!=0){
            if(value<4 && type.equals("up")){
                score = score+2;
            }
            if(value<6 && value>=4 && type.equals("down")){
                score = score+2;
            }
            if(value<8 && value>=6 && type.equals("left")){
                score = score+2;
            }
            if(value<11 && value>=8 && type.equals("right")){
                score = score+2;
            }
        }
    }

}
