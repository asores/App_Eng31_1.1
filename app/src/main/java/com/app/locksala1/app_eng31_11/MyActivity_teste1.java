package com.app.locksala1.app_eng31_11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.Serializable;
 
public class MyActivity_teste1 extends Activity implements Serializable{


    private String nome;
    private int senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity_menu);
    }

    public String getNome(){
     return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSenha(){
        return senha;
    }

    public void setSenha(Integer senha) {
        this.senha = senha;
    }
    public int getString(String senha) {
        return 1;
    }

    @Override
    public String toString(){
        return nome;
    }



    public void clickExit(MenuItem ite){
        Intent exit = new Intent(this, MyActivity_login.class);
        startActivity(exit);
        MyActivity_teste1.this.finish();
    }

    public void clickMenu(View view){
        Intent men = new Intent(this, MyActivity_home.class);
        startActivity(men);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
