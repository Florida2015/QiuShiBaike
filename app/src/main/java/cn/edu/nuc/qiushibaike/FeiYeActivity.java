package cn.edu.nuc.qiushibaike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FeiYeActivity extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fei_ye);
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}
