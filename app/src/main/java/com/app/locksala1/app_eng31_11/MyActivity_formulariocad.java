package com.app.locksala1.app_eng31_11;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MyActivity_formulariocad extends Activity {
    private ProgressDialog Progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity_formulariocad);
    }



    private void onCadastro(String usr, String psw) {

        Create_db db = new Create_db(this);
        db.abrir();
        db.insertReg(usr, psw);
        db.encerrar();
        Toast.makeText(this, "Dados inseridos com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void clickSubmit(View view){
        EditText usr = (EditText)findViewById(R.id.usr);
        EditText psw = (EditText)findViewById(R.id.psw);
        String user = usr.getText().toString();
        String pass = psw.getText().toString();
        if(user.equals("")){
            Toast.makeText(this, "Favor, preencha o usuario!", Toast.LENGTH_SHORT).show();
            onPause();
        }else if(pass.equals("")){
            Toast.makeText(this,"Favor, preencha a senha do usuario!",Toast.LENGTH_SHORT).show();
            onPause();
        }else{
            onCadastro(user,pass);
            ((EditText) findViewById(R.id.usr)).setText("");
            ((EditText) findViewById(R.id.psw)).setText("");

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity_formulariocad, menu);
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
        if (id == R.id.action_exit) {
            Intent intent = new Intent(MyActivity_formulariocad.this,MyActivity_login.class);
            startActivity(intent);
            MyActivity_formulariocad.this.finish();
            return true;
        }
        switch (item.getItemId()){

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
