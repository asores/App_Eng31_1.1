package com.app.locksala1.app_eng31_11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MyActivity extends Activity implements Runnable {

    private final int DURACAO_DA_TELA = 3500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MyActivity.this, MyActivity_login.class);
                MyActivity.this.startActivity(intent);
                MyActivity.this.finish();
            }
        }, DURACAO_DA_TELA);

    }

    @Override
    public void run() {

    }
}
