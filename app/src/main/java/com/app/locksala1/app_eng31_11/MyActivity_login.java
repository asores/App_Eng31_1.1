package com.app.locksala1.app_eng31_11;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MyActivity_login extends Activity implements HttpRequest.GetJSONListener {
    private Activity context;
    private MyActivity_login listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity_login);

        Button p = (Button) findViewById(R.id.button);

        //Capturamos o nome
        final EditText nome = (EditText) findViewById(R.id.editText);

        //Capturamos a senha
        final EditText pass = (EditText) findViewById(R.id.editText2);

        context = this;
        listener = this;

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                //Evento onclick no botão
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("nome",nome.getText().toString());
                data.put("senha",pass.getText().toString());


                //Passamos nossos parâmetros
                HttpRequest http = new HttpRequest(data,listener,HttpRequest.TYPEPOST);
                http.setContext(context);
                http.execute("http://10.0.0.25:98/web_service/");
            }
        });
    }

    //Call Back para ver se o usuário é válido ou não
    public void onRemoteCallComplete(JSONArray response){
        //TODO auto-generated method sub
        try {
            JSONObject json = (JSONObject) response.get(0);

            SharedPreferences preferences = getSharedPreferences("usuario-login", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            SharedPreferences preferenc = getSharedPreferences("user-nome", MODE_PRIVATE);
            SharedPreferences.Editor user = preferenc.edit();

            //Verificamos se o usuario é válido e guardamos nossa session com ShardePreferences
            if(json.getString("sucess").equals("1")){
                editor.putString("usuario","1");
                MyActivity_teste1 objeto = new MyActivity_teste1();
                objeto.setSenha(objeto.getString("senha"));
            }else{
                editor.putString("usuario","0");
            }

            //Gravamos na Session
            editor.commit();
            String s = preferences.getString("usuario","0");


            final EditText nome = (EditText) findViewById(R.id.editText);
            final EditText pass = (EditText) findViewById(R.id.editText2);
            HashMap<String,String> data = new HashMap<String, String>();
            data.put("nome",nome.getText().toString());
            data.put("senha",pass.getText().toString());

            //Verificamos se o Usuario é válido, caso NÃO, exibe a mensagem TOAST
            if(s.equals("0")) {
                Toast.makeText(context, "Usuario Inválido", Toast.LENGTH_LONG).show();

            }else{
                Intent myIntent = new Intent(context, MyActivity_home.class);
                MyActivity_login.this.startActivity(myIntent);

                Toast.makeText(context, "Parabens " + nome.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException e){
            //TODO auto-generated catch block
            e.printStackTrace();
        }

    }



    public void onRemoteCallFailed(){
        //TODO auto-generated method stub
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity_login, menu);
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