package com.example.rozrah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Reg extends AppCompatActivity {

    Button Reg_next;
    Button Reg_back;

    EditText Reg_name;
    EditText Reg_email;
    EditText Reg_password;
    EditText Reg_con_password;
    EditText Reg_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        Reg_next = (Button) findViewById(R.id.Reg_next);
        Reg_back = (Button) findViewById(R.id.Reg_back);

        Reg_name = (EditText) findViewById(R.id.Reg_name);
        Reg_email = (EditText) findViewById(R.id.Reg_email);
        Reg_password = (EditText) findViewById(R.id.Reg_password);
        Reg_con_password = (EditText) findViewById(R.id.Reg_con_password);
        Reg_number = (EditText) findViewById(R.id.Reg_number);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Reg_back:
                        Intent login_reg = new Intent(Reg.this, MainActivity.class);
                        startActivity(login_reg);
//                        setContentView(R.layout.reg);
                        break;
                    case R.id.Reg_next:
                        Intent card_reg = new Intent(Reg.this, Card.class);
                        card_reg.putExtra("name", Reg_name.getText().toString());
                        card_reg.putExtra("email", Reg_email.getText().toString());
                        card_reg.putExtra("password", Reg_password.getText().toString());
                        card_reg.putExtra("number ", Reg_number.getText().toString());
                        startActivity(card_reg);
//                        setContentView(R.layout.login);
                        break;
                }
            }
        };

        Reg_next.setOnClickListener(onClickListener);
        Reg_back.setOnClickListener(onClickListener);
    }
}
