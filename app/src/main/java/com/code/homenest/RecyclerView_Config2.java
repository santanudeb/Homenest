package com.code.homenest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerView_Config2 {
    private Context mContext;
    private EmployeesAdapter mEmployeesAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Employee> employees, List<String> keys){
        mContext=context;
        mEmployeesAdapter=new RecyclerView_Config2.EmployeesAdapter(employees, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mEmployeesAdapter);
    }

    class EmployeesView extends RecyclerView.ViewHolder {
        private TextView mPhone;
        private TextView mName;
        private TextView mEmail;
        private TextView mType;

        private String key;

        public EmployeesView (ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.employee_item, parent, false));

            mPhone=(TextView)itemView.findViewById(R.id.tv_emp_phone);
            mName=(TextView)itemView.findViewById(R.id.tv_emp_name);
            mEmail=(TextView)itemView.findViewById(R.id.tv_emp_email);
            mType=(TextView)itemView.findViewById(R.id.tv_emp_type);
        }

        public void bind(Employee employees, String key){
            mPhone.setText(employees.getPhone());  //change
            mName.setText(employees.getName());
            mEmail.setText(employees.getEmail());
            mType.setText(employees.getType());

            this.key=key;
        }
    }

    class EmployeesAdapter extends RecyclerView.Adapter<RecyclerView_Config2.EmployeesView> {
        private List<Employee> mEmployeesList;
        private List<String> mKeys;

        public EmployeesAdapter(List<Employee> mEmployeesList, List<String> mKeys) {
            this.mEmployeesList = mEmployeesList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RecyclerView_Config2.EmployeesView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView_Config2.EmployeesView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView_Config2.EmployeesView holder, int position) {
            holder.bind(mEmployeesList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mEmployeesList.size();
        }
    }
}
