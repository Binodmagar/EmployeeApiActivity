package com.binod.Api;

import com.binod.Model.Employee;
import com.binod.Model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployee();


    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);


    @GET("employees/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empID);
}
