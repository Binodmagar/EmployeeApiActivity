package com.binod.employeeapiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.binod.Api.EmployeeAPI;
import com.binod.Model.Employee;
import com.binod.Model.EmployeeCUD;
import com.binod.URL.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDeleteEmpActivity extends AppCompatActivity {

    EditText etSearchEmpU,etNameU,etSalaryU,etAgeU;
    Button btnsearchEmpU, btnUpdate, btnDelete;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_emp);

        etSearchEmpU = findViewById(R.id.etSearchEmpU);
        etNameU = findViewById(R.id.etNameU);
        etSearchEmpU = findViewById(R.id.etSearchEmpU);
        etSalaryU = findViewById(R.id.etSalaryU);
        etAgeU = findViewById(R.id.etAgeU);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnsearchEmpU = findViewById(R.id.btnsearchEmpU);

        btnsearchEmpU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateEmployee();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteEmployee();
            }
        });
    }

    public void CreateInstance(){
         retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         employeeAPI = retrofit.create(EmployeeAPI.class);
    }

    public void loadData(){
        CreateInstance();
        Call<Employee> employeeCall = employeeAPI.getEmployeeByID(Integer.parseInt(etSearchEmpU.getText().toString()));

        employeeCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                etNameU.setText(response.body().getEmployee_name());
                etSalaryU.setText(Float.toString(response.body().getEmployee_salary()));
                etAgeU.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateDeleteEmpActivity.this, "Errors" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateEmployee(){
        CreateInstance();
        EmployeeCUD employeeCUD = new EmployeeCUD(
                etNameU.getText().toString(),
                Float.parseFloat(etSalaryU.getText().toString()),
                Integer.parseInt(etAgeU.getText().toString())
        );

        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etSearchEmpU.getText().toString()), employeeCUD);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteEmpActivity.this, "Updated successfully!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteEmpActivity.this, "Errors" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteEmployee(){
        CreateInstance();
        Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etSearchEmpU.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteEmpActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteEmpActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
