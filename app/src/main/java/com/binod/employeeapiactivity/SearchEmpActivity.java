package com.binod.employeeapiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binod.Api.EmployeeAPI;
import com.binod.Model.Employee;
import com.binod.URL.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmpActivity extends AppCompatActivity {

    EditText etSearchEmp;
    Button btnSearchEmployee;
    TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_emp);

        etSearchEmp = findViewById(R.id.etSearchEmp);
        btnSearchEmployee = findViewById(R.id.btnSearchEmployee);
        tvData = findViewById(R.id.tvData);

        btnSearchEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private  void loadData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etSearchEmp.getText().toString()));


        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchEmpActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                String content = "";
                content += "ID: " + response.body().getId() + "\n";
                content += "Name: " + response.body().getEmployee_name();
                content += "Salary: " + response.body().getEmployee_salary();
                content += "Age: " + response.body().getEmployee_age();

                tvData.setText(content);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                Toast.makeText(SearchEmpActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
