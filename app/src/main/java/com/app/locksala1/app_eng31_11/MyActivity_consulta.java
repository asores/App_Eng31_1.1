package com.app.locksala1.app_eng31_11;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity_consulta extends Activity {

    int cont = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity_consulta);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        onCreate(cont);
    }

    private void onCreate(int cont) {

        Create_db db = new Create_db(this);
        TextView id = (TextView)findViewById(R.id.id);
        TextView nome = (TextView)findViewById(R.id.user);


        TextView pass = (TextView)findViewById(R.id.pass);
        db.abrir();
        //db.insertReg("alex.soares","lock123");
        String arrayI[] = db.ID();
        String arrayN[] = db.NOME();
        String arrayP[] = db.SENHA();
        if (arrayI.length > 0) {
            id.setText(arrayI[cont]);
            nome.setText(arrayN[cont]);
            pass.setText(arrayP[cont]);
        }else{
            Toast.makeText(this,"Não existe registros para esta consulta!",Toast.LENGTH_SHORT).show();
        }
        db.encerrar();
    }
    public void clickNext(View view){
        Create_db db = new Create_db(this);
        String arrayI[] = db.ID();
        if(cont < db.onCount() - 1) {
            cont++;
            onCreate(cont);
        }else if(cont == db.onCount() - 1){
            onCreate(cont);
            if (arrayI.length > 0){
                Toast.makeText(this,"Você esta no seu ultimo registro!",Toast.LENGTH_SHORT).show();
            }
        }
        if (arrayI.length == 0) {
            Toast.makeText(this, "Não existe registros para esta consulta!", Toast.LENGTH_SHORT).show();
        }

    }

    public void clickPrevius(View view){
        Create_db db = new Create_db(this);
        String arrayI[] = db.ID();
        if(cont > 0) {
            cont--;
            onCreate(cont);
        }else if(cont == 0)
        {
            onCreate(cont);
            if (arrayI.length > 0){
                Toast.makeText(this,"Você esta no seu primeiro registro!",Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity_consulta, menu);
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
            Intent intent = new Intent(MyActivity_consulta.this,MyActivity_login.class);
            startActivity(intent);
            MyActivity_consulta.this.finish();
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
