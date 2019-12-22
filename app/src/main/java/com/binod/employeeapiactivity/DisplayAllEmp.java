package com.binod.employeeapiactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.binod.Adapter.ShowEmpAdapter;
import com.binod.Api.EmployeeAPI;
import com.binod.Model.Employee;
import com.binod.URL.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayAllEmp extends AppCompatActivity {

//    TextView tvOutput;
    private static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_emp);

//        tvOutput = findViewById(R.id.tvOutput);
        recyclerView = findViewById(R.id.recycleView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall = employeeAPI.getAllEmployee();


        //Asynchronous call
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DisplayAllEmp.this, "Error code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Employee> employeeList = response.body();
//                employeeList.add(new Employee())

//                ShowEmpAdapter showEmpAdapter = new ShowEmpAdapter(this, employeeList);

//                for(Employee emp : employeeList){
//                    String data = "";
//                    data += "Name is: " + emp.getEmployee_name() + "\n";
//                    data += "Salary is: " + emp.getEmployee_salary() + "\n";
//                    data += "-----------------------------------------------------" + "\n";
//
//                    tvOutput.append(data);
//                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Msg", "onFailure" + t.getLocalizedMessage());
                Toast.makeText(DisplayAllEmp.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
