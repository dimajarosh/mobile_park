package com.example.rozrah;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button Login_reg;
    Button Login_next;
    TextView Login_login;
    EditText email;
    EditText password;
    DBHelper dbHelper;
//    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        FirebaseApp.initializeApp(this);
//        mAuth = FirebaseAuth.getInstance();
        dbHelper = new DBHelper(this);
        Login_reg = (Button) findViewById(R.id.Login_reg);
        Login_next = (Button) findViewById(R.id.Login_next);
        email = (EditText) findViewById(R.id.Login_email);
        password = (EditText) findViewById(R.id.Login_password);
        Login_login = (TextView) findViewById(R.id.Login_login);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Login_reg:
                        Intent reg_reg = new Intent(MainActivity.this, Reg.class);
                        startActivity(reg_reg);
//                        setContentView(R.layout.reg);
                        break;
                    case R.id.Login_next:
                        SQLiteDatabase database = dbHelper.getWritableDatabase();
                        Cursor cursor = database.query(DBHelper.TABLE_CLIENTS, null, null, null, null, null, null);
                        int flag = 0;
                        if(cursor.moveToFirst()) {
//                      int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
                            int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                            do {
                                if(Objects.equals(cursor.getString(emailIndex), email.getText().toString()) && Objects.equals(cursor.getString(passwordIndex), password.getText().toString())) {
//                                    Login_login.setText(cursor.getString(passwordIndex));
                                    flag = 1;
                                }
//                                Example.setText(cursor.getString(emailIndex));
                            }
                            while(cursor.moveToNext());
                            if(flag == 1) {
                                Intent reg_account = new Intent(MainActivity.this, Account.class);
                                startActivity(reg_account);
                            }
                        }

                        cursor.close();
//                        String query = "SELECT "+ DBHelper.KEY_ID + " FROM " + DBHelper.TABLE_CLIENTS +
//                                " WHERE " + DBHelper.KEY_EMAIL + "=?" + " AND " +
//                                DBHelper.KEY_PASSWORD + "=?";
//                        SQLiteDatabase database = dbHelper.getWritableDatabase();
//
//                        Cursor cursor = database.rawQuery(query, new String[] {email.getText().toString(), password.getText().toString()});
//                        Login_login.setText(cursor.getCount());
////                        int client_id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID));
////                        Log.w("SQLITE", "ge" + client_id);
////                        Login_login.setText("lox");
//                        cursor.close();

//                        setContentView(R.layout.login);
                        break;
                }
            }
        };

        Login_reg.setOnClickListener(onClickListener);
        Login_next.setOnClickListener(onClickListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Login_reg:
                setContentView(R.layout.reg);
                break;
            case R.id.Reg_back:
                setContentView(R.layout.login);
                break;
        }
    }
}
