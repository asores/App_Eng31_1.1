package com.app.locksala1.app_eng31_11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

/**
 * Created by locksala1 on 01/08/14.
 */


public class Create_db extends SQLiteOpenHelper{

    public Create_db(Context ctx) {
        super(ctx, "Mybd", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE usuarios ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                                               "user TEXT NOT NULL, password TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version_ant, int version_nue) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }

    public void insertReg(String user, String pass){

        ContentValues valores = new ContentValues();
        valores.put("user", user);
        valores.put("password", pass);
        this.getWritableDatabase().insert("usuarios",null,valores);
    }

    public int onCount(){
        String count_query = "SELECT DISTINCT "+_ID+" FROM usuarios;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(count_query,null);
        int count = c.getCount();
        c.close();
        return count;

    }

    public String[] ID(){

        String result[] = new String[onCount()];
        String colunas[] = {_ID,"user","password"};
        Cursor c = this.getReadableDatabase().query("usuarios",colunas,null,null,null,null,null);

        int id,iu,ip;
        id = c.getColumnIndex(_ID);
        //iu = c.getColumnIndex("user");
        //ip = c.getColumnIndex("password");

        int contI = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result[contI] = c.getString(id)+"\n";
            contI++;
        }
        return result;

        //c.moveToNext();
        //c.moveToFirst();
        //result = c.getString(id)+"\n";
    }
    public String[] NOME(){

        String result[] = new String[onCount()];
        String colunas[] = {_ID,"user","password"};
        Cursor c = this.getReadableDatabase().query("usuarios",colunas,null,null,null,null,null);

        int id,iu,ip;
        //id = c.getColumnIndex(_ID);
        iu = c.getColumnIndex("user");
        //ip = c.getColumnIndex("password");

        int contN = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result[contN] = c.getString(iu)+"\n";
            contN++;
        }
        return result;
        //result = c.getString(iu)+"\n";
    }

    public String[] SENHA(){
        String result[] = new String[onCount()];
        String colunas[] = {_ID,"user","password"};
        Cursor c = this.getReadableDatabase().query("usuarios",colunas,null,null,null,null,null);

        int id,iu,ip;
        //id = c.getColumnIndex(_ID);
        //iu = c.getColumnIndex("user");
        ip = c.getColumnIndex("password");

        int contP = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result[contP] = c.getString(ip) +"\n";
            contP++;
        }
        return result;
        //result = c.getString(ip) +"\n";
    }




    public void abrir(){
        this.getWritableDatabase();
    }

    public void encerrar(){
        this.close();
    }
}
