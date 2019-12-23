package com.binod.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binod.Model.Employee;
import com.binod.employeeapiactivity.R;

import java.util.List;

public class ShowEmpAdapter extends RecyclerView.Adapter<ShowEmpAdapter.ShowEmpViewHolder> {

    Context context;
    List<Employee> employeeList;


    public ShowEmpAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public ShowEmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_showemp,parent,false);
        return new ShowEmpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowEmpViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.tvIdR.setText("ID: " + Integer.toString(employee.getId()));
        holder.tvNameR.setText("Name: " + employee.getEmployee_name());
        holder.tvSalaryR.setText("Salary: " + Float.toString(employee.getEmployee_salary()));
        holder.tvAgeR.setText("Age: " + Integer.toString(employee.getEmployee_age()));

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ShowEmpViewHolder extends RecyclerView.ViewHolder{
        TextView tvIdR, tvNameR, tvSalaryR, tvAgeR;

        public ShowEmpViewHolder(@NonNull View itemView) {
            super(itemView);

            //binding
            tvIdR = itemView.findViewById(R.id.tvIdR);
            tvNameR = itemView.findViewById(R.id.tvNameR);
            tvSalaryR = itemView.findViewById(R.id.tvSalaryR);
            tvAgeR = itemView.findViewById(R.id.tvAgeR);
        }
    }
}
