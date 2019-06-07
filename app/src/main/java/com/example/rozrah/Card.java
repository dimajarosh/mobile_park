package com.example.rozrah;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Card extends AppCompatActivity {

    Button Prev_card;
    Button Done_card;
    TextView Example;

    EditText nof;
    EditText cvv;
    EditText months;
    EditText years;
    DBHelper dbHelper;

    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
//        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        dbHelper = new DBHelper(this);

        Prev_card = (Button) findViewById(R.id.Prev_card);
        Done_card = (Button) findViewById(R.id.Done_card);
        Example = (TextView) findViewById(R.id.card);

        nof = (EditText) findViewById(R.id.number_card);
        months = (EditText) findViewById(R.id.months);
        years = (EditText) findViewById(R.id.year);
        cvv = (EditText) findViewById(R.id.cvv_card);

//        if(cursor.moveToFirst()) {
////            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
//            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
//
//            do {
//                Example.setText(cursor.getString(emailIndex));
//            }
//            while(cursor.moveToNext());
//        }
//
//        cursor.close();
//        Example.setText(name);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Done_card:
                        Intent intent = getIntent();
                        String name = intent.getStringExtra("name");
                        String password = intent.getStringExtra("password");
                        String email = intent.getStringExtra("email");
                        String number = intent.getStringExtra("number");

                        SQLiteDatabase database = dbHelper.getWritableDatabase();

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DBHelper.KEY_EMAIL, email);
                        contentValues.put(DBHelper.KEY_PASSWORD, password);
                        contentValues.put(DBHelper.KEY_NAME, name);
                        contentValues.put(DBHelper.KEY_NUMBER, number);
                        contentValues.put(DBHelper.KEY_NOF, nof.getText().toString());
                        contentValues.put(DBHelper.KEY_MONTHS, months.getText().toString());
                        contentValues.put(DBHelper.KEY_YEARS, years.getText().toString());
                        contentValues.put(DBHelper.KEY_CVV, cvv.getText().toString());

                        database.insert(DBHelper.TABLE_CLIENTS, null, contentValues);

                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Card.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
//                                            long t = 1;
//                                            Log.d(TAG, "createUserWithEmail:success");
//                                            FirebaseUser user = mAuth.getCurrentUser();
//                                            updateUI(user);
                                        } else {
//                                            long t = 0;
                                            // If sign in fails, display a message to the user.
//                                            Log.d(TAG, "createUserWithEmail:failure", task.getException());
//                                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                                    Toast.LENGTH_SHORT).show();
//                                            updateUI(null);
                                        }

                                        // ...
                                    }
                                });
//                        Cursor cursor = database.query(DBHelper.TABLE_CLIENTS, null, null, null, null, null, null);
//
                        Intent login_card = new Intent(Card.this, MainActivity.class);
                        startActivity(login_card);
//                        setContentView(R.layout.reg);
                        break;
                    case R.id.Prev_card:
                        Intent reg_card = new Intent(Card.this, Reg.class);
                        startActivity(reg_card);
//                        setContentView(R.layout.login);
                        break;
                }
            }
        };

        Prev_card.setOnClickListener(onClickListener);
        Done_card.setOnClickListener(onClickListener);
    }
}
