package com.binod.Api;

import com.binod.Model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployee();


    @GET("employees/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empID);
}
