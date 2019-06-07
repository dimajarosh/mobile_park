package com.example.rozrah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.Objects;
//import android.widget.Toast;

public class Account extends AppCompatActivity {

    String[] streets = {"Кульпарківська", "Стрийська"};
    String[] houses_1 = {"4", "6", "12", "17"};
    String[] houses_2 = {"3", "7", "8", "12", "17"};
    String[] places_1 = {"1", "2", "3", "4", "5"};
    String[] places_2 = {"1", "2", "3", "4"};
    String select_street;
    String select_house;
    String select_place;

    Date times;

    Button back;
    Button pay;
    Button start;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);

        ArrayAdapter<String> adapter_street = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, streets);
        adapter_street.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapter_house1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, houses_1);
        adapter_house1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapter_house2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, houses_2);
        adapter_house2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapter_places1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, places_1);
        adapter_places1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapter_places2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, places_2);
        adapter_places2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner_street = (Spinner) findViewById(R.id.spinner_street);
        final Spinner spinner_house = (Spinner) findViewById(R.id.spinner_house);
        final Spinner spinner_place = (Spinner) findViewById(R.id.spinner_place);
        spinner_street.setAdapter(adapter_street);
        spinner_house.setAdapter(adapter_house1);
        spinner_place.setAdapter(adapter_places1);

        AdapterView.OnItemSelectedListener itemSelectedListener_street = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                if(Objects.equals(item, "Кульпарківська")) {
                    select_street = item;
                    spinner_house.setAdapter(adapter_house1);
                    spinner_place.setAdapter(adapter_places1);
                } else {
                    spinner_house.setAdapter(adapter_house2);
                    spinner_place.setAdapter(adapter_places2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        AdapterView.OnItemSelectedListener itemSelectedListener_house = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                select_house = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        AdapterView.OnItemSelectedListener itemSelectedListener_place = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                select_place = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinner_street.setOnItemSelectedListener(itemSelectedListener_street);
        spinner_house.setOnItemSelectedListener(itemSelectedListener_house);
        spinner_place.setOnItemSelectedListener(itemSelectedListener_place);

        back = (Button) findViewById(R.id.Account_back);
        pay = (Button) findViewById(R.id.Account_pay);
        pay.setClickable(false);
        pay.setVisibility(View.GONE);
        start = (Button) findViewById(R.id.Account_start);
        output = (TextView) findViewById(R.id.Account_output);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Account_back:
                        Intent acc_log = new Intent(Account.this, MainActivity.class);
                        startActivity(acc_log);
//                        setContentView(R.layout.reg);
                        break;
                    case R.id.Account_pay:

                        pay.setClickable(false);
                        pay.setVisibility(View.GONE);
                        start.setClickable(true);
                        Date times2 = new Date();
                        long seconds = (times2.getTime() - times.getTime());
                        seconds = seconds * 2;
                        long gryvnia = seconds/1000/100;
                        long kop = seconds/1000 % 100;
                        output.setText("Оплата: "+ gryvnia + " грн. "+ kop + " коп.");

                        spinner_street.setClickable(true);
                        spinner_street.setEnabled(true);
                        spinner_house.setClickable(true);
                        spinner_house.setEnabled(true);
                        spinner_place.setClickable(true);
                        spinner_place.setEnabled(true);
                        break;

                    case R.id.Account_start:
                        pay.setClickable(true);
                        pay.setVisibility(View.VISIBLE);
                        start.setClickable(false);
                        times = new Date();
                        spinner_street.setClickable(false);
                        spinner_street.setEnabled(false);
                        spinner_house.setClickable(false);
                        spinner_house.setEnabled(false);
                        spinner_place.setClickable(false);
                        spinner_place.setEnabled(false);
                        break;
                }
            }
        };

        back.setOnClickListener(onClickListener);
        pay.setOnClickListener(onClickListener);
        start.setOnClickListener(onClickListener);

    }
}
