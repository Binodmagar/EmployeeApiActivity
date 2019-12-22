package com.binod.employeeapiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnShowEmp, btnRegisterEmp, btnSearchEmp,btnUpdateDeleteEmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnShowEmp = findViewById(R.id.btnShowEmp);
        btnRegisterEmp = findViewById(R.id.btnRegisterEmp);
        btnSearchEmp = findViewById(R.id.btnSearchEmp);
        btnUpdateDeleteEmp = findViewById(R.id.btnUpdateDeleteEmp);

        btnSearchEmp.setOnClickListener(this);
        btnRegisterEmp.setOnClickListener(this);
        btnShowEmp.setOnClickListener(this);
        btnUpdateDeleteEmp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnShowEmp :
                Intent intent = new Intent(MainActivity.this, DisplayAllEmp.class);
                startActivity(intent);
                break;

            case R.id.btnRegisterEmp :
                Intent intent1 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent1);
                break;

            case  R.id.btnSearchEmp :
                Intent intent2 = new Intent(MainActivity.this, SearchEmpActivity.class);
                startActivity(intent2);
                break;

            case R.id.btnUpdateDeleteEmp :
                Intent intent3 = new Intent(MainActivity.this, UpdateDeleteEmpActivity.class);
                startActivity(intent3);
                break;

        }
    }
}
